package com.damda.ideaconcert.damda.alarm.dto;

import lombok.Data;

@Data
public class AlarmReq {
    private int userId;
    private int targetId;
    private String alarmType;
}
