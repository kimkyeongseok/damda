package com.damda.ideaconcert.damda.post.representation;

import java.util.List;

import lombok.Data;

@Data
public class PostUploadRequest {
    private String nickName;
    private String snsUrl;
    private String imgUrl;
    private String postType;
    private List<String> hashTag;
}
