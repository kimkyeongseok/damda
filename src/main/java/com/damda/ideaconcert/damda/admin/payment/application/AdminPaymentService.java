package com.damda.ideaconcert.damda.admin.payment.application;

import java.util.List;

import com.damda.ideaconcert.damda.admin.payment.representation.AdminPaymentReadDto;

public interface AdminPaymentService {
    long total();
    List<AdminPaymentReadDto> read();
    
}
