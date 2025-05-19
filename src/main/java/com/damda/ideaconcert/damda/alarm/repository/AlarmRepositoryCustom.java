package com.damda.ideaconcert.damda.alarm.repository;


import com.damda.ideaconcert.damda.alarm.dto.AlarmRes;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlarmRepositoryCustom {
    List<AlarmRes> findByUserId(int userId, String start, String end);
}
