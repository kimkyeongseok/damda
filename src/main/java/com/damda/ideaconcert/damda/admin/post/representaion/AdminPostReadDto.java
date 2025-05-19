package com.damda.ideaconcert.damda.admin.post.representaion;

import java.time.Instant;
import java.util.List;

import lombok.Value;

@Value
public class AdminPostReadDto {
    Integer id;
    String userId;
    String writer;
    String snsUrl;
    String imgUrl;
    Integer likeCount;
    Instant uploadDate;
    List<String> hashTag;
}
