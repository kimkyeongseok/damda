package com.damda.ideaconcert.damda.admin.user.representation;

import java.time.Instant;

import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String userId;
    String nickName;
    Instant signUpDate;
    Instant loginDate;
    String userAgent;
    boolean deactivate;
}
