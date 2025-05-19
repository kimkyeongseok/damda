package com.damda.ideaconcert.damda.payment.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Integer> {
	List<Payment> findByUserPk(int userPk);
	List<Payment> findByUserPkOrderByDateDesc(int userPk);
	Optional<Payment> findByUserPkAndProductId(int userPk, Integer productId);
    
}
