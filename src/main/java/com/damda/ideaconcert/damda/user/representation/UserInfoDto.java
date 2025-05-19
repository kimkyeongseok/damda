package com.damda.ideaconcert.damda.user.representation;

import lombok.Value;

@Value
public class UserInfoDto {
    Integer id;
    String userId;
    String nickName;
    String imgUrl;
    String snsUrl;
    String userEmail;
}
