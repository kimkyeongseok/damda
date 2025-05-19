package com.damda.ideaconcert.damda.comment.dto;

import lombok.Data;

@Data
public class CommentInsertReq {
    private int post_id;
    private Integer comment_id;
    private int level;
    private String content;
}
