package com.damda.ideaconcert.damda.following.controller;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.follower.dto.FollowerReq;
import com.damda.ideaconcert.damda.follower.service.FollowerService;
import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.following.dto.FollowingReq;
import com.damda.ideaconcert.damda.following.dto.FollowingRes;
import com.damda.ideaconcert.damda.following.service.FollowingService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import io.swagger.annotations.ApiOperation;
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
public class FollowingController {
    private final FollowingService followingService;

    @ApiOperation(value = "팔로잉", notes = "팔로잉하기")
    @PostMapping("/following")
    public ResponseEntity<Following> followerInsert(@RequestBody FollowingReq req,@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        followingService.followingInsert(req,userPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "언팔로우", notes = "언팔로우하기")
    @PatchMapping("/unfollow/{followingNo}")
    public ResponseEntity<?> unfollow(@PathVariable Integer followingNo,@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        followingService.unfollow(followingNo,userPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "팔로우여부", notes = "상대방 팔로우여부")
    @GetMapping("/following/confirm/{target_id}")
    public ResponseEntity<?> confirmFollow(@PathVariable Integer target_id){
        String Yn = followingService.followingYn(target_id);
        return new ResponseEntity<>(Yn,HttpStatus.OK);
    }
    @ApiOperation(value = "팔로잉개수", notes = "내가 팔로잉한개수")
    @GetMapping("/following/count")
    public ResponseEntity<?> FollowingCnt(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        Long cnt = followingService.followingCnt(userPk);
        return new ResponseEntity<>(cnt,HttpStatus.OK);
    }
    @ApiOperation(value = "팔로워개수", notes = "팔로워 한개수")
    @GetMapping("/follower/count")
    public ResponseEntity<?> FollowerCnt(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        Long cnt = followingService.followerCnt(userPk);
        return new ResponseEntity<>(cnt,HttpStatus.OK);
    }
    @ApiOperation(value = "팔로워개수", notes = "팔로워 한개수")
    @GetMapping("/follower-list")
    public ResponseEntity<List<FollowingRes>> FollowerList(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        List<FollowingRes> list = followingService.followerList(userPk);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @ApiOperation(value = "팔로워개수", notes = "팔로워 한개수")
    @GetMapping("/following-list")
    public ResponseEntity<List<FollowingRes>> FollowingList(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        List<FollowingRes> list = followingService.followingList(userPk);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
