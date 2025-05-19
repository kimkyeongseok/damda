package com.damda.ideaconcert.damda.mystorage.controller;

import com.damda.ideaconcert.damda.comment.dto.CommentRes;
import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.following.dto.FollowingReq;
import com.damda.ideaconcert.damda.mystorage.domain.MyStorage;
import com.damda.ideaconcert.damda.mystorage.dto.MyStorageInsertReq;
import com.damda.ideaconcert.damda.mystorage.service.MyStorageService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class MyStorageController {
    private final MyStorageService myStorageService;

    @GetMapping("/my-storage")
    public ResponseEntity<List<MyStorage>> myStorageList(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());

        List<MyStorage> list = myStorageService.myStorageList(userPk);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/my-storage")
    public ResponseEntity<Following> myStorageInsert(@RequestBody MyStorageInsertReq req, @CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());

        myStorageService.myStorageInsert(userPk,req);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/my-storage/{myStorageNo}")
    public ResponseEntity<Follower> myStorageDelete(@PathVariable Integer myStorageNo){
        myStorageService.myStorageDelete(myStorageNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
