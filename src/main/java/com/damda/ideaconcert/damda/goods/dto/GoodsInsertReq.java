package com.damda.ideaconcert.damda.goods.dto;

import lombok.Data;

@Data
public class GoodsInsertReq {
    private String subject;
    private String type;
    private String mobile;
    private int price;
    private int month;
}
