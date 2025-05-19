package com.damda.ideaconcert.damda.following.service;

import com.damda.ideaconcert.damda.alarm.domain.Alarm;
import com.damda.ideaconcert.damda.alarm.dto.AlarmReq;
import com.damda.ideaconcert.damda.alarm.service.AlarmService;
import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.following.dto.FollowingReq;
import com.damda.ideaconcert.damda.following.dto.FollowingRes;
import com.damda.ideaconcert.damda.following.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowingService {
    private final FollowingRepository followingRepository;
    private final AlarmService alarmService;

    @Transactional
    public void followingInsert(FollowingReq req,Integer my_id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Following following = Following.builder()
                .my_id(my_id)
                .target_id(req.getTarget_id())
                .del_yn("N")
                .regdate(now_dt)
                .build();

       Following saveFollowing =  followingRepository.save(following);

       Alarm alarm = Alarm.builder()
               .user_id(my_id)
               .target_id(req.getTarget_id())
               .alarm_type("F")
               .build();
       alarmService.alarmInsert(alarm);
    }
    @Transactional
    public void unfollow(Integer follow_no,int userPk){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Following unfollow = followingRepository.findByIds(follow_no,userPk);

        unfollow.unfollow(
                "Y",
                now_dt
        );
    }
    public String followingYn(int target_id){
        Long cnt = followingRepository.findByCnt(target_id);
        String follow ;
        if(cnt > 0 ){
            follow = "Y";
        }else{
            follow = "N";
        }
        return follow;
    }
    public Long followingCnt(int my_id){
        Long cnt = followingRepository.findByMyIdCnt(my_id);
        return cnt;
    }
    public Long followerCnt(int targetId){
        Long cnt = followingRepository.findByTargetIdCnt(targetId);
        return cnt;
    }
    public List<FollowingRes> followingList(int myId){
        List<FollowingRes> following = followingRepository.findByMyId(myId);
        return following;
    }
    public List<FollowingRes> followerList(int myId){
        List<FollowingRes> following = followingRepository.findByTargetId(myId);
        return following;
    }

}
