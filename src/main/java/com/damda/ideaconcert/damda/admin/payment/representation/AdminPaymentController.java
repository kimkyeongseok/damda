package com.damda.ideaconcert.damda.admin.payment.representation;

import java.util.List;

import com.damda.ideaconcert.damda.admin.payment.application.AdminPaymentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/management/payments")
@RequiredArgsConstructor
public class AdminPaymentController {
    private final AdminPaymentService paymentService;

    @GetMapping("/total")
    public long getTotal() {
        return paymentService.total();
    }

    @GetMapping("")
    public List<AdminPaymentReadDto> getPaymentList() {
        return paymentService.read();
    }
    
}
