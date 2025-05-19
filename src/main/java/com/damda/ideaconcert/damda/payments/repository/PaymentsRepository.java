package com.damda.ideaconcert.damda.payments.repository;

import com.damda.ideaconcert.damda.payments.domain.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments,Integer>,PaymentsRepositoryCustom {
}
