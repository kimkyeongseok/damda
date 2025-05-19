package com.damda.ideaconcert.damda.payments.controller;

import com.damda.ideaconcert.damda.payments.domain.Payments;
import com.damda.ideaconcert.damda.payments.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @GetMapping("/payment-list")
    public ResponseEntity<List<Payments>> paymentList(){
        List<Payments> list = paymentsService.paymentsList();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
