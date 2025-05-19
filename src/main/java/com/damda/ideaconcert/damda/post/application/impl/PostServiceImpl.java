package com.damda.ideaconcert.damda.post.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.damda.ideaconcert.damda.admin.post.domain.DeletePostEvent;
import com.damda.ideaconcert.damda.alarm.domain.Alarm;
import com.damda.ideaconcert.damda.alarm.service.AlarmService;
import com.damda.ideaconcert.damda.common.application.EventService;
import com.damda.ideaconcert.damda.filemanager.application.FileManager;

import com.damda.ideaconcert.damda.following.dto.FollowingRes;
import com.damda.ideaconcert.damda.following.service.FollowingService;
import com.damda.ideaconcert.damda.post.application.*;
import com.damda.ideaconcert.damda.post.domain.HashTag;
import com.damda.ideaconcert.damda.post.domain.HashTagRepository;
import com.damda.ideaconcert.damda.post.domain.Post;
import com.damda.ideaconcert.damda.post.domain.PostHashTag;
import com.damda.ideaconcert.damda.post.domain.PostHashTagRepository;
import com.damda.ideaconcert.damda.post.domain.PostLike;
import com.damda.ideaconcert.damda.post.domain.PostLikeRepository;
import com.damda.ideaconcert.damda.post.domain.PostRepository;
import com.damda.ideaconcert.damda.post.representation.LikedPostReadResponse;
import com.damda.ideaconcert.damda.post.representation.PostDetailResponse;
import com.damda.ideaconcert.damda.post.representation.PostLikeResponse;
import com.damda.ideaconcert.damda.post.representation.PostUpdateRequest;
import com.damda.ideaconcert.damda.post.representation.PostUploadRequest;
import com.damda.ideaconcert.damda.post.representation.UserPostReadResponse;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final FileManager fileManager;
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostHashTagRepository postHashTagRepository;
    private final EventService eventService;
    private final AlarmService alarmService;
    private final FollowingService followingService;

    @Override
    @Transactional
    public void create(PostUploadRequest request, int userPk) {
        String imgUrl = fileManager.base64ToImage(request.getImgUrl());
        Post post = new Post(
            userPk,
            request.getNickName(),
            request.getSnsUrl(),
            imgUrl,
            request.getPostType()
        );

        post = postRepository.save(post);
        List<FollowingRes> followingList = followingService.followingList(userPk);
        List<Alarm> result = new ArrayList<>();
        for (FollowingRes following : followingList) {
            Alarm alarm = Alarm.builder()
                    .user_id(following.getMyId())
                    .target_id(following.getTargetId())
                    .alarm_type("P")
                    .build();
            result.add(alarm);
        }
        alarmService.alarmInsert(result);

        for(String tag : request.getHashTag()) {
            HashTag hashTag = new HashTag(tag);
            hashTag = hashTagRepository.save(hashTag);
            PostHashTag postHashTag = new PostHashTag();
            post.addPostHashTag(postHashTag);
            hashTag.addPostHashTag(postHashTag,hashTag);
        }
    }
    
    @Override
    @Transactional
    public UserPostReadResponse read(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> posts = page.getContent();
        Set<Integer> userIds = 
            posts.stream().map(it -> it.getUserPk()).collect(Collectors.toSet());
        List<User> users = userRepository.findByIdIn(userIds);
        List<PostReadDto> postList = new ArrayList<>();

        Map<Integer, User> result = 
            users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        for(int i=0; i < posts.size(); i++) {
            Post post = posts.get(i);
            User user = result.get(post.getUserPk());
            PostReadDto postReadDto = new PostReadDto(
                post.getId(),
                user.getNickName(),
                user.getImgUrl(),
                post.getSnsUrl(),
                post.getImgUrl(),
                post.getPostType(),
                user.getId(),
                post.getLikeCount(),
                post.getUploadDate()
            );
            postList.add(postReadDto);
        }
        return new UserPostReadResponse(
            postList, 
            page.getTotalPages(), 
            page.getNumber(), 
            page.getSize(),
            page.getSort()
        );
    }

    @Override
    @Transactional
    public void update(PostUpdateRequest request, int userPk) {
        Post post = postRepository.findByIdAndUserPk(request.getId(), userPk)
            .orElseThrow(() -> new NoSuchElementException());
        postHashTagRepository.deleteByPostId(post.getId());
        
        for(String tag : request.getHashTag()) {
            HashTag hashTag = new HashTag(tag);
            hashTag = hashTagRepository.save(hashTag);
 
            PostHashTag postHashTag = new PostHashTag();
            post.addPostHashTag(postHashTag);
            hashTag.addPostHashTag(postHashTag,hashTag);
        }
        Post updatePost = new Post(
            post.getUserPk(),
            request.getNickName(), 
            request.getSnsUrl(), 
            post.getImgUrl(),
            request.getPostType()
        );
        post.update(updatePost);
    }

    @Override
    @Transactional
    public void delete(int postId, int userPk) {
        Post post = postRepository.findByIdAndUserPk(postId, userPk).orElseThrow(() -> new NoSuchElementException());
        String uploadImg = post.getImgUrl();
        DeletePostEvent deletePostEvent = new DeletePostEvent(uploadImg);
        postLikeRepository.deleteByPostId(postId);
        eventService.publish(deletePostEvent);
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional
    public PostLikeResponse like(int postId, int userPk) {
        Post post = postRepository.findById(postId).orElse(null);
        Optional<PostLike> likedPost = postLikeRepository.findByPostIdAndUserPk(postId, userPk);

        likedPost.ifPresentOrElse(
            postLike -> {
                // post.unLike(post);
                // postLikeRepository.delete(postLike);
            }, 
            () -> {
                PostLike postLike = new PostLike(
                    postId,
                    userPk
                );
                post.like(post);
                postLikeRepository.save(postLike);
            }
        );
        Alarm alarm = Alarm.builder()
                .user_id(post.getUserPk())
                .target_id(userPk)
                .alarm_type("L")
                .post_id(post.getId())
                .build();
        alarmService.alarmInsert(alarm);
        return new PostLikeResponse(
            true,
            post.getLikeCount()
        );
    }
    @Override
    @Transactional
    public PostLikeResponse unlike(int postId, int userPk) {
        Post post = postRepository.findById(postId).orElse(null);
        
        Optional<PostLike> likedPost = 
            postLikeRepository.findByPostIdAndUserPk(postId, userPk);
        
        likedPost.ifPresentOrElse(
            postLike -> {
                post.unLike(post);
                postLikeRepository.delete(postLike);
            }, 
            () -> {
                // PostLike postLike = new PostLike(
                //     post.getId(),
                //     user.getId()
                // );
                // post.like(post);
                // postLikeRepository.save(postLike);
            }
        );
        return new PostLikeResponse(
            false,
            post.getLikeCount()
        );
    }
    @Override
    @Transactional
    public PostLikeResponse getPostLike(int postId, int userPk) {
        Optional<PostLike> likedPost = 
            postLikeRepository.findByPostIdAndUserPk(postId, userPk);
        
        return new PostLikeResponse(
            likedPost.isPresent(),
            null
        );
    }

    @Override
    @Transactional
    public LikedPostReadResponse getLikedPosts(int userPk, Pageable pageable) {
        Page<PostLike> page = postLikeRepository.findByUserPkOrderByLikedDateDesc(userPk, pageable);
        List<PostLike> likedPostId = page.getContent();
        List<Integer> postIds = 
            likedPostId.stream().map(it -> it.getPostId()).collect(Collectors.toList());
        List<LikedPostReadDto> postList = new ArrayList<>();
        List<Post> posts = postRepository.findByIdIn(postIds);
        
        Map<Integer,Post> result = 
            posts.stream().collect(Collectors.toMap(Post::getId, Function.identity()));
        if(posts.size() > 0){
            for(int i=0; i < postIds.size(); i++){
                Post post = result.get(postIds.get(i));
                LikedPostReadDto postReadDto = new LikedPostReadDto(
                    post.getId(), 
                    post.getNickName(),
                    post.getSnsUrl(), 
                    post.getImgUrl(),
                    post.getPostType(),
                    post.getLikeCount(), 
                    post.getUploadDate()
                );
                postList.add(postReadDto);
            }
        }
 
        return new LikedPostReadResponse(
            postList, 
            page.getTotalPages(), 
            page.getNumber(), 
            page.getSize(),
            page.getSort()
        );
    }

    @Override
    @Transactional
    public UserPostReadResponse getUploadedPosts(int userPk, Pageable pageable) {
        Page<Post> page = postRepository.findByUserPkOrderByUploadDateDesc(userPk, pageable);
        List<Post> posts = page.getContent();
        List<UploadedPostReadDto> postList = new ArrayList<>();
        for(int i=0; i< posts.size(); i++) {
            Post post = posts.get(i);
            UploadedPostReadDto postReadDto = new UploadedPostReadDto(
                post.getId(), 
                post.getNickName(),
                post.getSnsUrl(), 
                post.getImgUrl(),
                post.getPostType(),
                post.getLikeCount(), 
                post.getUploadDate()
            );
            postList.add(postReadDto);
        }     
        return new UserPostReadResponse(
            postList, 
            page.getTotalPages(), 
            page.getNumber(), 
            page.getSize(),
            page.getSort()
        );
    }

    @Override
    public PostDetailResponse readPostDetail(int postId, int userPk) {
        List<PostHashTag> postHashTag = postHashTagRepository.findByPostId(postId);
        Optional<PostLike> likedPost = postLikeRepository.findByPostIdAndUserPk(postId, userPk);

        return new PostDetailResponse(
            likedPost.isPresent(),
            hashTags(postHashTag) 
        );
    }

    private List<String> hashTags(List<PostHashTag> list) {
        List<String> hashTags = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            hashTags.add(list.get(i).getHashTag().getName());
        }  
        return hashTags;
    }
    public PostLikeUserDto userNickFind(int post_id,int user_id){
        PostLike postLikeUser = postLikeRepository.findByIds(post_id);
        User user = userRepository.findByIds(postLikeUser.getUserPk());
        PostLikeUserDto postLikeUserDto = new PostLikeUserDto();
        if(postLikeUser != null) {
            postLikeUserDto.setId(postLikeUser.getId());
            postLikeUserDto.setNickName(user.getNickName());
            postLikeUserDto.setImgUrl(user.getImgUrl());

            return postLikeUserDto;
        }else {
            return null;
        }
    }

}