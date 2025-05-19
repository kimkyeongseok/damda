package com.damda.ideaconcert.damda.payment.application;

import com.damda.ideaconcert.damda.payment.representation.AppPaymentRequest;
import com.damda.ideaconcert.damda.payment.representation.AppPaymentResponse;
import com.damda.ideaconcert.damda.payment.representation.CurrentPointResponse;
import com.damda.ideaconcert.damda.payment.representation.PaymentCompleteRequest;
import com.damda.ideaconcert.damda.payment.representation.PurchaseRequest;
import com.damda.ideaconcert.damda.payment.representation.PurchaseResponse;

public interface PaymentService {
    UserPaymentInfoDto getUserPaymentInfo(int userPk);
	boolean complete(PaymentCompleteRequest paymentCompleteRequest, int userPk);
	boolean validatePayment(int userPk, int productId);
	AppPaymentResponse appPaymentComplete(int userPk, AppPaymentRequest paymentRequest);
	PurchaseResponse purchase(int userPk, PurchaseRequest paymentRequest);
	CurrentPointResponse getUserCurrentPoint(int userPk);
}
