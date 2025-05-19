package com.damda.ideaconcert.damda.alarm.controller;

import com.damda.ideaconcert.damda.alarm.dto.AlarmRes;
import com.damda.ideaconcert.damda.alarm.service.AlarmService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping("/alarm-list")
    public ResponseEntity<List<AlarmRes>> alarmList(@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());

        List<AlarmRes> list = alarmService.alarmList(userPk);

        return new ResponseEntity<>(list,HttpStatus.OK);

    }
}
