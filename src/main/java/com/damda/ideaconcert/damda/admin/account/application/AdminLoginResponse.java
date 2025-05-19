package com.damda.ideaconcert.damda.admin.account.application;

import lombok.Value;

@Value
public class AdminLoginResponse {
    String accessToken;
    String message;
}
