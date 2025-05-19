package com.damda.ideaconcert.damda.goods.service;

import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.damda.ideaconcert.damda.goods.dto.GoodsInsertReq;
import com.damda.ideaconcert.damda.goods.dto.GoodsRes;
import com.damda.ideaconcert.damda.goods.dto.GoodsUpdateReq;
import com.damda.ideaconcert.damda.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public List<GoodsRes> subscribeGoodsList(){
        List<GoodsRes> goodsRes = goodsRepository.subscribeByListAll();

        if(goodsRes !=null) {
            return goodsRes;
        }else{
            return null;
        }
    }
    @Transactional
    public void subscribeGoodsInsert(GoodsInsertReq req){
        //현재 날짜 가져오는 부분
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Goods goods = Goods.builder()
                .subject(req.getSubject())
                .type(req.getType())
                .mobile(req.getMobile())
                .price(req.getPrice())
                .month(req.getMonth())
                .regdate(now_dt)
                .build();

        goodsRepository.save(goods);
    }
    @Transactional
    public void subscribeGoodsUpdate(Integer subscribeGoodsNo, GoodsUpdateReq req){

        Goods goods = goodsRepository.subscribeGoodsByIds(subscribeGoodsNo);

        goods.goodsUpdate(
                req.getSubject(),
                req.getType(),
                req.getMobile(),
                req.getPrice(),
                req.getMonth()
        );
    }
    @Transactional
    public void subscribeGoodsDelete(Integer subscribeGoodsNo){
        Goods goods = goodsRepository.subscribeGoodsByIds(subscribeGoodsNo);

        goods.goodsDelete(
                "Y"
        );

    }
}
