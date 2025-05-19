package com.damda.ideaconcert.damda.alarm.service;

import com.damda.ideaconcert.damda.alarm.domain.Alarm;
import com.damda.ideaconcert.damda.alarm.dto.AlarmRes;
import com.damda.ideaconcert.damda.alarm.repository.AlarmRepository;
import com.damda.ideaconcert.damda.user.application.UserService;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.representation.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserService userService;
    public List<AlarmRes> alarmList(int userId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime now = LocalDateTime.now();
        String start = yesterday.format(formatter);
        String end = now.format(formatter);

        List<AlarmRes> alarmList =  alarmRepository.findByUserId(userId,start,end);
        List<AlarmRes> alarmResList = new ArrayList<>();
        for (AlarmRes alarmRes : alarmList) {
            UserInfoDto userInfo = userService.getUserInfo(alarmRes.getTargetId());
            alarmRes.setTargetNickName(userInfo.getNickName());
            alarmRes.setTargetProfileImgUrl(userInfo.getImgUrl());
            alarmResList.add(alarmRes);
        }

        return alarmResList;
    }
    @Transactional
    public void alarmInsert(List<Alarm> req){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        List<Alarm> result = req.stream()
                .map(alarm -> Alarm.builder()
                        .user_id(alarm.getUser_id())
                        .target_id(alarm.getTarget_id())
                        .alarm_type(alarm.getAlarm_type())
                        .regdate(now_dt)
                        .build())
                .collect(Collectors.toList());
        alarmRepository.saveAll(result);
    }
    @Transactional
    public void alarmInsert(Alarm req){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Alarm alarm = Alarm.builder()
                .user_id(req.getUser_id())
                .target_id(req.getTarget_id())
                .alarm_type(req.getAlarm_type())
                .post_id(req.getPost_id())
                .regdate(now_dt)
                .build();

        alarmRepository.save(alarm);
    }

}
