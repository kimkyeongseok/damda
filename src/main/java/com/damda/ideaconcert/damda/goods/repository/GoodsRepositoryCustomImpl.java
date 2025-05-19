package com.damda.ideaconcert.damda.goods.repository;

import com.damda.ideaconcert.damda.goods.domain.QGoods;
import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.damda.ideaconcert.damda.goods.dto.QGoodsRes;
import com.damda.ideaconcert.damda.goods.dto.GoodsRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.goods.domain.QGoods.goods;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class GoodsRepositoryCustomImpl implements GoodsRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<GoodsRes> subscribeByListAll() {
        return jpaQueryFactory.select(new QGoodsRes(goods))
                .from(goods)
                .orderBy(goods.regdate.desc())
                .fetch();
    }

    @Override
    public Goods subscribeGoodsByIds(Integer subscribeGoodsNo) {
        return jpaQueryFactory.select(new QGoods(goods))
                .from(goods)
                .where(goods.no.eq(subscribeGoodsNo))
                .fetchOne();
    }
}
