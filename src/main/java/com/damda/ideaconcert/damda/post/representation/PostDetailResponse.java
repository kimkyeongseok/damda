package com.damda.ideaconcert.damda.post.representation;

import java.util.List;

import lombok.Value;

@Value
public class PostDetailResponse {
    boolean like;
    List<String> hashTag;
}
