package com.damda.ideaconcert.damda.payment.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Value;

@Value
public class AppPaymentResponse {
    @JsonIgnore
    int code;
    int currentPoint;
    String msg;
}
