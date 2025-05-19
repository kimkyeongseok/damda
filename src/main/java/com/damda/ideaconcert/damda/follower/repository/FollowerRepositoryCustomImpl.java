package com.damda.ideaconcert.damda.follower.repository;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import com.damda.ideaconcert.damda.follower.domain.QFollower;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.follower.domain.QFollower.follower;
@RequiredArgsConstructor
@Repository
public class FollowerRepositoryCustomImpl implements FollowerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Follower findByIds(Integer idx) {
        return jpaQueryFactory.select(new QFollower(follower))
                .from(follower)
                .where(follower.no.eq(idx))
                .fetchOne();
    }

    @Override
    public Long findByCnt(int target_id) {
        return jpaQueryFactory.select(follower.count())
                .from(follower)
                .where(follower.target_id.eq(target_id),follower.del_yn.eq("N"))
                .fetchOne();
    }
}
