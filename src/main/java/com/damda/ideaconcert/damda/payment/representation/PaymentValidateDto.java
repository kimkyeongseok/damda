package com.damda.ideaconcert.damda.payment.representation;

import lombok.Value;

@Value
public class PaymentValidateDto {
    Integer status;
    String message;
}
