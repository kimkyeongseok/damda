package com.damda.ideaconcert.damda.admin.account.representation;

import lombok.Value;

@Value
public class AdminLoginRequestDto {
    String adminId;
    String adminPw;
}
