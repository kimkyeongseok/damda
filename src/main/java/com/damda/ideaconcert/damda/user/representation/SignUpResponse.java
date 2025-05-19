package com.damda.ideaconcert.damda.user.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Value;

@Value
public class SignUpResponse {
    @JsonIgnore
    Integer code;
    String desc;
    String message;
}
