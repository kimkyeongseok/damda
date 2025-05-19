package com.damda.ideaconcert.damda.post.application;

import com.damda.ideaconcert.damda.post.domain.PostLike;
import com.damda.ideaconcert.damda.user.domain.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostLikeUserDto {
    private Integer id;
    private String nickName;
    private String imgUrl;
}
