package com.damda.ideaconcert.damda.post.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Value;

@Value
public class PostLikeResponse {
    boolean like;
    @JsonInclude(Include.NON_NULL)
    Integer likeCount;

}
