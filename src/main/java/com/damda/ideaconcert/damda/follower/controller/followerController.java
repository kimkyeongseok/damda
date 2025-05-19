package com.damda.ideaconcert.damda.follower.controller;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.follower.dto.FollowerReq;
import com.damda.ideaconcert.damda.follower.service.FollowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class followerController {

    private final FollowerService followerService;
    /*
    @PostMapping("/follower")
    public ResponseEntity<Follower> followerInsert(@RequestBody FollowerReq req){
        followerService.followerInsert(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/unfollow/{followerNo}")
    public ResponseEntity<Follower> unfollow(@PathVariable Integer followerNo){
        followerService.unfollow(followerNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
    @GetMapping("/follower/confirm/{target_id}")
    public ResponseEntity<?> confirmFollow(@PathVariable Integer target_id){
        String Yn = followerService.followerYn(target_id);
        return new ResponseEntity<>(Yn,HttpStatus.OK);
    }
}
