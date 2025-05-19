package com.damda.ideaconcert.damda.user.application;

import lombok.Value;

@Value
public class Payload {
    String userPk;
    String userId;
    String role;
}
