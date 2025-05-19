package com.damda.ideaconcert.damda.follower.repository;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.following.domain.Following;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepositoryCustom {
    Follower findByIds(Integer idx);
    Long findByCnt(int target_id);
}
