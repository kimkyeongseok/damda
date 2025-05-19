package com.damda.ideaconcert.damda.payment.application;

import java.util.Map;

import lombok.Getter;

@Getter
public class ImpResponse {
    private String code;
    private Map<String, Object> response;
}
