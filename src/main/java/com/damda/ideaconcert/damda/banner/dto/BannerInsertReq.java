package com.damda.ideaconcert.damda.banner.dto;

import lombok.Data;

@Data
public class BannerInsertReq {
    private int userId;
    private String bannerType;
    private String gender;
    private String email;
    private int old;
    private String area;
    private String profession;
    private String bannerImgUrl;
}
