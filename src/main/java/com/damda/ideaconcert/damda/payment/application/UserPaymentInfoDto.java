package com.damda.ideaconcert.damda.payment.application;

import java.util.List;

import com.damda.ideaconcert.damda.payment.domain.Payment;

import lombok.Value;

@Value
public class UserPaymentInfoDto {
    Integer id;
    String userId;
    String nickName;
    String snsUrl;
    List<Payment> paymentList;
}
