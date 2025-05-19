package com.damda.ideaconcert.damda.payment.representation;

import lombok.Getter;

@Getter

public class AppPaymentRequest {
    private String store;
    private String productId;
    private String transactId;
    private String purchaseToken;
}
