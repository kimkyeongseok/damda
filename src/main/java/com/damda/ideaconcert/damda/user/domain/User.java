package com.damda.ideaconcert.damda.user.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.damda.ideaconcert.damda.user.representation.UserUpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String nickName;
    private String userPw;
    private String userEmail;
    @Transient
    private String userPwCnf;
    private String snsUrl;

    private Instant loginDate;
    private Instant signUpDate;

    private boolean deactivate;
    private Instant deactivateDate;
   
    private String token;
    private String role;
    private boolean isAuth;
    private String authKey;
    private String userAgent;
    private String imgUrl;
    private String selectReception;

    // public void like(PostLike postLike) {
    //     this.likedPosts.add(postLike);
    //     postLike.setUser(this);
    // }

    // public void cancelLike(PostLike postLike) {
    //     this.likedPosts.remove(postLike);
    //     postLike.setUser(this);
    // }
    public void update(UserUpdateRequest updateUserRequest,String ImgUrl){
       this.setNickName(updateUserRequest.getNickName());
       this.setSnsUrl(updateUserRequest.getSnsUrl());
       this.setImgUrl(ImgUrl);
    }
    public void login(String accessToken,String userAgent){
        this.token = accessToken;
        this.userAgent = userAgent;
        this.loginDate = Instant.now();
    }
}