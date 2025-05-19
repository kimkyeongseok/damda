package com.damda.ideaconcert.damda.payment.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    Optional<Point> findByUserPk(int userPk);
    
}
