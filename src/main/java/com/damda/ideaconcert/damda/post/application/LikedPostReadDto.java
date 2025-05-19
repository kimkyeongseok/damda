package com.damda.ideaconcert.damda.post.application;

import java.time.Instant;

import lombok.Value;

@Value
public class LikedPostReadDto {
    Integer id;
    String writer;
    String snsUrl;
    String imgUrl;
    String postType;
    Integer likeCount;
    Instant uploadDate;
}
