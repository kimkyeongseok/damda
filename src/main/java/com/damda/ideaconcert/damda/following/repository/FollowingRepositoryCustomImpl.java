package com.damda.ideaconcert.damda.following.repository;

import com.damda.ideaconcert.damda.following.domain.Following;
import com.damda.ideaconcert.damda.following.domain.QFollowing;
import com.damda.ideaconcert.damda.following.dto.FollowingRes;
import com.damda.ideaconcert.damda.following.dto.QFollowingRes;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.damda.ideaconcert.damda.following.domain.QFollowing.following;
import static com.damda.ideaconcert.damda.user.domain.QUser.user;


@RequiredArgsConstructor
@Repository
public class FollowingRepositoryCustomImpl implements FollowingRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Following findByIds(Integer idx,int myId) {
        return jpaQueryFactory.select(new QFollowing(following))
                .from(following)
                .where(following.target_id.eq(idx),following.my_id.eq(myId),following.del_yn.eq("N"))
                .fetchOne();
    }

    @Override
    public List<FollowingRes> findByMyId(int myId) {
        return jpaQueryFactory.select(new QFollowingRes(following,user))
                .from(following)
                .join(user).on(user.id.eq(following.target_id),following.del_yn.eq("N"))
                .where(following.my_id.eq(myId))
                .fetch();
    }
    @Override
    public List<FollowingRes> findByTargetId(int myId) {
        return jpaQueryFactory.select(new QFollowingRes(following,user))
                .from(following)
                .join(user).on(user.id.eq(following.my_id),following.del_yn.eq("N"))
                .where(following.target_id.eq(myId))
                .fetch();
    }

    @Override
    public Long findByCnt(int target_id) {
        return jpaQueryFactory.select(following.count())
                .from(following)
                .where(following.target_id.eq(target_id),following.del_yn.eq("N"))
                .fetchOne();
    }

    @Override
    public Long findByMyIdCnt(int my_id) {
        return jpaQueryFactory.select(following.count())
                .from(following)
                .where(following.my_id.eq(my_id),following.del_yn.eq("N"))
                .fetchOne();
    }

    @Override
    public Long findByTargetIdCnt(int target_id) {
        return jpaQueryFactory.select(following.count())
                .from(following)
                .where(following.target_id.eq(target_id),following.del_yn.eq("N"))
                .fetchOne();
    }

}
