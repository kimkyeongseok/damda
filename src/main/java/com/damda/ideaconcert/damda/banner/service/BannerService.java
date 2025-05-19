package com.damda.ideaconcert.damda.banner.service;

import com.damda.ideaconcert.damda.banner.domain.Banner;
import com.damda.ideaconcert.damda.banner.dto.BannerInsertReq;
import com.damda.ideaconcert.damda.banner.repository.BannerRepository;
import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {
    private final FileManager fileManager;
    private final BannerRepository bannerRepository;

    public void bannerInsert(BannerInsertReq req,int userId){
        //현재 날짜 가져오는 부분
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        String imgUrl = fileManager.base64ToImage(req.getBannerImgUrl());

        Banner banner = Banner.builder()
                .user_id(userId)
                .banner_type(req.getBannerType())
                .gender(req.getGender())
                .email(req.getEmail())
                .old(req.getOld())
                .area(req.getArea())
                .profession(req.getProfession())
                .banner_img_url(imgUrl)
                .regdate(now_dt)
                .build();
        bannerRepository.save(banner);

    }
}
