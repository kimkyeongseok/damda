package com.damda.ideaconcert.damda.post.representation;

import com.damda.ideaconcert.damda.post.application.PostLikeUserDto;
import com.damda.ideaconcert.damda.post.application.PostService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/feed/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public UserPostReadResponse read(
            @PageableDefault(size=12,sort="uploadDate", direction = Direction.DESC) 
            Pageable pageable
        ) {
        return postService.read(pageable);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody PostUploadRequest request, @CookieValue(name = "_dawt") String token) {
        int userPk = getUserPkFromJWT(token);
        postService.create(request, userPk);  
        return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
    }
    @PutMapping("")
    public void update(@RequestBody PostUpdateRequest request, @CookieValue(name = "_dawt") String token){
        int userPk = getUserPkFromJWT(token);
        postService.update(request, userPk);
    }
    
    @DeleteMapping("/{postId}")
    public void delete(@PathVariable("postId") Integer postId, @CookieValue(name = "_dawt") String token){
        int userPk = getUserPkFromJWT(token);
        postService.delete(postId, userPk);
    }
    @GetMapping("/uploaded")
    public UserPostReadResponse getUploadedPosts(
            @CookieValue(name = "_dawt") String token,
            @PageableDefault(size = 10) 
            Pageable pageable
        ) {
        int userPk = getUserPkFromJWT(token);
        return postService.getUploadedPosts(userPk, pageable);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<PostLikeResponse> like(@PathVariable("postId") int postId, @CookieValue(name = "_dawt") String token) {
        int userPk = getUserPkFromJWT(token);
        PostLikeResponse response = postService.like(postId, userPk);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/unlike/{postId}")
    public ResponseEntity<PostLikeResponse> unlike(@PathVariable("postId") int postId, @CookieValue(name = "_dawt") String token) {
        int userPk = getUserPkFromJWT(token);
        PostLikeResponse response = postService.unlike(postId, userPk);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/like/{postId}")
    public ResponseEntity<PostLikeResponse> isLike(@PathVariable("postId") int postId, @CookieValue(name = "_dawt") String token) {
        int userPk = getUserPkFromJWT(token);
        PostLikeResponse response = postService.getPostLike(postId, userPk);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/liked")
    public LikedPostReadResponse getLikedPosts(
            @CookieValue(name = "_dawt") String token,
            @PageableDefault(size = 10)
            Pageable pageable
        ) {
        int userPk = getUserPkFromJWT(token);
        return postService.getLikedPosts(userPk, pageable);
    }
    @GetMapping("/liked/nickName/{post_id}")
    public ResponseEntity<PostLikeUserDto> getLikedPostUserNick(@PathVariable int post_id, @CookieValue(name = "_dawt") String token) {
        int userPk = getUserPkFromJWT(token);
        PostLikeUserDto postLikeUser = postService.userNickFind(post_id,userPk);
        return new ResponseEntity<>(postLikeUser, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPostDetail(
            @PathVariable("postId") int postId,
            @CookieValue(name = "_dawt") String token
        ){
        int userPk = getUserPkFromJWT(token);
        PostDetailResponse response = postService.readPostDetail(postId, userPk);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }

    private int getUserPkFromJWT(String token) {
        int userPk = Integer.parseInt(DecodeJWT.decode(token, 1).get("aud").toString());
        return userPk;
    }
}
