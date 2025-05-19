package com.damda.ideaconcert.damda.subscribe.controller;

import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import com.damda.ideaconcert.damda.subscribe.dto.SubscribeRes;
import com.damda.ideaconcert.damda.subscribe.serivce.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class SubscribeController {
    private final SubscribeService subscribeService;

    @GetMapping("/subscribe/{user_id}")
    public ResponseEntity<SubscribeRes> SubscribeByUserId(@PathVariable Integer user_id){
        SubscribeRes subscribe = subscribeService.subscribeByUserId(user_id);
        return new ResponseEntity<>(subscribe, HttpStatus.OK);
    }
}
