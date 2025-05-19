package com.damda.ideaconcert.damda.following.repository;

import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.following.dto.FollowingRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepositoryCustom {
    Following findByIds(Integer idx,int myId);
    List<FollowingRes> findByMyId(int myId);
    List<FollowingRes> findByTargetId(int targetId);
    Long findByCnt(int target_id);
    Long findByMyIdCnt(int my_id);
    Long findByTargetIdCnt(int target_id);
}
