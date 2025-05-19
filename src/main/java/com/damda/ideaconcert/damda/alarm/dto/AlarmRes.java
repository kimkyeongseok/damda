package com.damda.ideaconcert.damda.alarm.dto;

import com.damda.ideaconcert.damda.alarm.domain.Alarm;
import com.damda.ideaconcert.damda.post.domain.Post;
import com.damda.ideaconcert.damda.user.domain.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AlarmRes {
    private Integer no;
    private int userId;
    private int targetId;
    private String targetNickName;
    private String targetProfileImgUrl;
    private String postImgUrl;
    private String alarmType;
    private String regdate;

    @QueryProjection
    public AlarmRes(Alarm alarm, User user, Post post){
        this.no = alarm.getNo();
        this.userId = alarm.getUser_id();
        this.targetId = alarm.getTarget_id();
        this.postImgUrl = post.getImgUrl();
        this.alarmType = alarm.getAlarm_type();
        this.regdate = alarm.getRegdate();
    }

}
