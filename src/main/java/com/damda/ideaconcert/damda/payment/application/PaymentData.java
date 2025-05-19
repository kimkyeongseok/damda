package com.damda.ideaconcert.damda.payment.application;

import java.util.Map;

import lombok.Getter;

@Getter
public class PaymentData {
    String code;
    Map<String, Object> response;
}
