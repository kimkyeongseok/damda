package com.damda.ideaconcert.damda.banner.dto;

import lombok.Data;

@Data
public class BannerRes {
    private Integer no;
    private int userId;
    private String bannerType;
    private String gender;
    private String email;
    private int old;
    private String area;
    private String profession;
    private String bannerImgUrl;
    private String regdate;
}
