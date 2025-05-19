package com.damda.ideaconcert.damda.subscribe.repository;

import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import com.damda.ideaconcert.damda.subscribe.dto.SubscribeRes;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepositoryCustom {
    SubscribeRes subscribeByUserId(Integer userId);

}
