package com.damda.ideaconcert.damda.goods.repository;

import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.damda.ideaconcert.damda.goods.dto.GoodsRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepositoryCustom {
    List<GoodsRes> subscribeByListAll();
    Goods subscribeGoodsByIds(Integer subscribeGoodsNo);
}
