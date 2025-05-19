package com.damda.ideaconcert.damda.user.application;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString   
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUserRequest {
    private String userId;
    private String nickName;
    private String userPw;
    private String userEmail;
    @Transient
    private String userPwCnf;
    private String userAgent;
    private String selectReception;
}
