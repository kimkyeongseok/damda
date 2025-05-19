package com.damda.ideaconcert.damda.post.application;

import java.time.Instant;

import lombok.Value;

@Value
public class PostReadDto {
    Integer id;
    String writer;
    String profileImgUrl;
    String snsUrl;
    String imgUrl;
    String postType;
    Integer userPk;
    Integer likeCount;
    Instant uploadDate;


}
