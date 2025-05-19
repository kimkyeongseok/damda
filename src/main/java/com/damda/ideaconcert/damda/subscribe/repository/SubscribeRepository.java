package com.damda.ideaconcert.damda.subscribe.repository;

import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe,Integer>,SubscribeRepositoryCustom {
}
