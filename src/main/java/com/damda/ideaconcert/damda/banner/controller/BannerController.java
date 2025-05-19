package com.damda.ideaconcert.damda.banner.controller;

import com.damda.ideaconcert.damda.banner.dto.BannerInsertReq;
import com.damda.ideaconcert.damda.banner.service.BannerService;
import com.damda.ideaconcert.damda.comment.dto.CommentInsertReq;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class BannerController {
    private final BannerService bannerService;

    @PostMapping("/banner")
    public ResponseEntity<?> bannerInsert(@RequestBody BannerInsertReq req, @CookieValue("_dawt") String jwt){

        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());

        bannerService.bannerInsert(req,userPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
