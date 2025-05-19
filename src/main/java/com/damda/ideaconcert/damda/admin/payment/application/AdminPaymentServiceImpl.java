package com.damda.ideaconcert.damda.admin.payment.application;

import java.util.List;
import java.util.stream.Collectors;

import com.damda.ideaconcert.damda.admin.payment.representation.AdminPaymentReadDto;
import com.damda.ideaconcert.damda.payment.domain.Payment;
import com.damda.ideaconcert.damda.payment.domain.PaymentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPaymentServiceImpl implements AdminPaymentService{
    private final PaymentRepository paymentRepository;
    @Override
    public long total() {
        return paymentRepository.count();
    }

    @Override
    public List<AdminPaymentReadDto> read() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<AdminPaymentReadDto> response = paymentList.stream().map(payment -> {
            
            return new AdminPaymentReadDto(
                payment.getOrderId(),
                payment.getUserPk().toString(),
                payment.getProduct().getName(),
                payment.getDate()
            );
        }).collect(Collectors.toList());
        return response;
    }
    
}
