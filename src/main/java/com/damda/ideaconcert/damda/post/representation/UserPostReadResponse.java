package com.damda.ideaconcert.damda.post.representation;

import org.springframework.data.domain.Sort;

import lombok.Value;

@Value
public class UserPostReadResponse {
    Object postList;
    Integer totalPage;
    Integer pageNumber;
    Integer size;
    Sort sort;
}
