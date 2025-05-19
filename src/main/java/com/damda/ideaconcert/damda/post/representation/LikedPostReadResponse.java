package com.damda.ideaconcert.damda.post.representation;

import java.util.List;

import com.damda.ideaconcert.damda.post.application.LikedPostReadDto;

import org.springframework.data.domain.Sort;

import lombok.Value;

@Value
public class LikedPostReadResponse {
    List<LikedPostReadDto> postList;
    Integer totalPage;
    Integer pageNumber;
    Integer size;
    Sort sort;
}
