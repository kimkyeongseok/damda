package com.damda.ideaconcert.damda.post.representation;

import java.util.List;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private Integer id;
    private String nickName;
    private String snsUrl;
    private String postType;
    private List<String> hashTag;
}
