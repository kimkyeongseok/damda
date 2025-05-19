package com.damda.ideaconcert.damda.alarm.repository;


import com.damda.ideaconcert.damda.alarm.dto.AlarmRes;
import com.damda.ideaconcert.damda.alarm.dto.QAlarmRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.alarm.domain.QAlarm.alarm;
import static com.damda.ideaconcert.damda.post.domain.QPost.post;
import static com.damda.ideaconcert.damda.user.domain.QUser.user;


import java.util.List;
@RequiredArgsConstructor
@Repository
public class AlarmRepositoryCustomImpl implements AlarmRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AlarmRes> findByUserId(int userId, String start, String end) {
        return jpaQueryFactory.select(new QAlarmRes(alarm,user,post))
                .from(alarm)
                .join(user).on(user.id.eq(alarm.user_id))
                .join(post).on(post.id.eq(alarm.post_id))
                .where(alarm.user_id.eq(userId).and(alarm.regdate.between(start,end)))
                .orderBy(alarm.regdate.desc())
                .fetch();
    }
}
