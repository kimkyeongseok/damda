package com.damda.ideaconcert.damda.admin.post.application;

import java.util.List;
import java.util.stream.Collectors;

import com.damda.ideaconcert.damda.admin.post.domain.DeletePostEvent;
import com.damda.ideaconcert.damda.admin.post.representaion.AdminPostReadDto;
import com.damda.ideaconcert.damda.common.application.EventService;
import com.damda.ideaconcert.damda.post.domain.Post;
import com.damda.ideaconcert.damda.post.domain.PostLikeRepository;
import com.damda.ideaconcert.damda.post.domain.PostRepository;
import com.damda.ideaconcert.damda.user.domain.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPostServiceImpl implements AdminPostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final EventService eventService;
    
    @Override
    @Transactional
    public List<AdminPostReadDto> read() {
        List<Post> postList = postRepository.findAll();
        List<AdminPostReadDto> response = postList.stream().map(post -> {
            List<String> hashTags = post.getPostHashTag().stream().map(tag -> {
                return tag.getHashTag().getName();
            }).collect(Collectors.toList());
            String userId = userRepository.findById(post.getUserPk()).get().getUserId();
            return new AdminPostReadDto(
                post.getId(),
                userId,
                post.getNickName(),
                post.getSnsUrl(),
                post.getImgUrl(),
                post.getLikeCount(),
                post.getUploadDate(),
                hashTags
            );
        }).collect(Collectors.toList());
        return response;
    }

    @Override
    @Transactional
    public void delete(int postId) {
        Post post = postRepository.findById(postId).orElse(null);
        String uploadImg = post.getImgUrl();
        DeletePostEvent deletePostEvent = new DeletePostEvent(uploadImg);
        postLikeRepository.deleteByPostId(postId);
        eventService.publish(deletePostEvent);
        postRepository.deleteById(postId);
    }

    @Override
    public long total() {
        return postRepository.count();
    }
    
}
