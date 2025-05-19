package com.damda.ideaconcert.damda.subscribe.dto;

import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.damda.ideaconcert.damda.subscribe.domain.Subscribe;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class SubscribeRes {
    private Integer no;
    private Integer userId;
    private Integer paymentId;
    private Integer goodsId;
    private String subject;
    private Long feedCnt;
    private String startDate;
    private String endDate;
    private String useYn;

    @QueryProjection
    public SubscribeRes(Subscribe subscribe, Goods goods, Long feedCnt){
        this.no = subscribe.getNo();
        this.userId = subscribe.getUser_id();
        this.paymentId = subscribe.getPayment_id();
        this.goodsId = subscribe.getGoods_id();
        this.subject = goods.getSubject();
        this.feedCnt = feedCnt;
        this.startDate = subscribe.getStart_date();
        this.endDate = subscribe.getEnd_date();
    }
}
