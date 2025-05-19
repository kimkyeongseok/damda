package com.damda.ideaconcert.damda.payment.representation;

import lombok.Getter;

@Getter
public class PaymentCompleteRequest {
    private String impUid;
    private String merchantUid;
    private Integer productId;
}
