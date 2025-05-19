package com.damda.ideaconcert.damda.user.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Value;

@Value
public class LoginResponse {
    @JsonIgnore
    Integer code;
    String desc;
    String message;
    // String user;
    @JsonInclude(Include.NON_NULL)
    String accessToken; 
}
