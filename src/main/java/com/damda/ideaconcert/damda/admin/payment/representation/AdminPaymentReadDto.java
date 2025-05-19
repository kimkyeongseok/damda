package com.damda.ideaconcert.damda.admin.payment.representation;

import java.time.Instant;

import lombok.Value;

@Value
public class AdminPaymentReadDto {
    String orderId;
    String buyer;
    String product;
    Instant date;

}
