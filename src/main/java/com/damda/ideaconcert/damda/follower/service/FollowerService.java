package com.damda.ideaconcert.damda.follower.service;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.follower.dto.FollowerReq;
import com.damda.ideaconcert.damda.follower.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    @Transactional
    public void followerInsert(FollowerReq req){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Follower follower  = Follower.builder()
                .target_id(req.getTarget_id())
                .my_id(req.getMy_id())
                .regdate(now_dt)
                .build();
        followerRepository.save(follower);
    }
    @Transactional
    public void unfollow(Integer follower_id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Follower unfollow = followerRepository.findByIds(follower_id);
        unfollow.unfollow(
                "Y",
                now_dt
        );
    }

    public String followerYn(int target_id){
        Long cnt = followerRepository.findByCnt(target_id);
        String follow ;
        if(cnt > 0){
            follow = "Y";
        }else{
            follow = "N";
        }
        return follow;
    }

}
