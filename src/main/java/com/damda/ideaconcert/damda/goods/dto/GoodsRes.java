package com.damda.ideaconcert.damda.goods.dto;

import com.damda.ideaconcert.damda.goods.domain.Goods;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class GoodsRes {
    private Integer no;
    private String subject;
    private String type;
    private String mobile;
    private int price;
    private int month;
    private String delYn;
    private String regdate;

    @QueryProjection
    public GoodsRes(Goods goods){
        this.no = goods.getNo();
        this.subject = goods.getSubject();
        this.type = goods.getType();
        this.mobile = goods.getMobile();
        this.price = goods.getPrice();
        this.month = goods.getMonth();
        this.delYn = goods.getDelYn();
        this.regdate = goods.getRegdate();
    }
}
