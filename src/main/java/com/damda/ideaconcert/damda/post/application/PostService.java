package com.damda.ideaconcert.damda.post.application;

import com.damda.ideaconcert.damda.post.representation.LikedPostReadResponse;
import com.damda.ideaconcert.damda.post.representation.PostDetailResponse;
import com.damda.ideaconcert.damda.post.representation.PostLikeResponse;
import com.damda.ideaconcert.damda.post.representation.PostUpdateRequest;
import com.damda.ideaconcert.damda.post.representation.PostUploadRequest;
import com.damda.ideaconcert.damda.post.representation.UserPostReadResponse;

import org.springframework.data.domain.Pageable;

public interface PostService {
    void create(PostUploadRequest request, int userPk);
    UserPostReadResponse read(Pageable pageable);
    void update(PostUpdateRequest request, int userPk);
    void delete(int postId, int userPk);
    PostLikeResponse like(int postId, int userPk);
    PostLikeResponse unlike(int postId, int userPk);
    PostLikeResponse getPostLike(int postId, int userPk);
    LikedPostReadResponse getLikedPosts(int userPk, Pageable pageable);
    UserPostReadResponse getUploadedPosts(int userPk, Pageable pageable);
    PostDetailResponse readPostDetail(int postId, int userPk);
    PostLikeUserDto userNickFind(int post_id,int user_id);
}
