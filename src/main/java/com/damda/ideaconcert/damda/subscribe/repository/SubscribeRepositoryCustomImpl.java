package com.damda.ideaconcert.damda.subscribe.repository;

import com.damda.ideaconcert.damda.subscribe.domain.QSubscribe;
import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import com.damda.ideaconcert.damda.subscribe.dto.QSubscribeRes;
import com.damda.ideaconcert.damda.subscribe.dto.SubscribeRes;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.subscribe.domain.QSubscribe.subscribe;
import static com.damda.ideaconcert.damda.goods.domain.QGoods.goods;
import static com.damda.ideaconcert.damda.post.domain.QPost.post;

@RequiredArgsConstructor
@Repository
public class SubscribeRepositoryCustomImpl implements SubscribeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public SubscribeRes subscribeByUserId(Integer userId) {
        return jpaQueryFactory.select(new QSubscribeRes(subscribe,goods,
                        JPAExpressions.select(post.id.count())
                                .from(post)
                                .where(post.userPk.eq(subscribe.user_id))
                        ))
                .from(subscribe)
                .where(subscribe.user_id.eq(userId))
                .orderBy(subscribe.end_date.desc())
                .fetchOne();
    }
}
