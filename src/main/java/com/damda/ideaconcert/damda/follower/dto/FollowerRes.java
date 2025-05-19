package com.damda.ideaconcert.damda.follower.dto;

import lombok.Data;

@Data
public class FollowerRes {
    private Integer no;
    private int target_id;
    private int my_id;
    private String regdate;
}
