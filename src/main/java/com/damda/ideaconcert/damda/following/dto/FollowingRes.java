package com.damda.ideaconcert.damda.following.dto;

import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.user.domain.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class FollowingRes {
    private Integer no;
    private int myId;
    private int targetId;
    private String nickName;
    private String userId;
    private String imgUrl;
    private String regdate;

    @QueryProjection
    public FollowingRes(Following following, User user){
        this.no = following.getNo();
        this.myId = following.getMy_id();
        this.targetId = following.getTarget_id();
        this.nickName = user.getNickName();
        this.userId = user.getUserId();
        this.imgUrl = user.getImgUrl();
        this.regdate = following.getRegdate();
    }
}
