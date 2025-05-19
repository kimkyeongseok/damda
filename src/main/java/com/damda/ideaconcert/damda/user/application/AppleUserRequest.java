package com.damda.ideaconcert.damda.user.application;

import java.beans.ConstructorProperties;

public class AppleUserRequest  extends AbstractUserRequest{
    @ConstructorProperties({
        "userId",
        "nickName",
        "userPw",
        "userEmail",
        "userPwCnf",
        "userAgent",
        "selectReception"
    })
    public AppleUserRequest() {
    }
    public AppleUserRequest(
        String userId, 
        String nickName, 
        String userPw,
        String userEmail,
        String userPwCnf,
        String userAgent,
        String selectReception
    ) {
        super(
            userId, 
            nickName, 
            userPw,
            userEmail,
            userPwCnf, 
            userAgent,
            selectReception
        );
    }
}
