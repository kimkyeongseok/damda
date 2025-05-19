package com.damda.ideaconcert.damda.goods.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public Goods(Integer no, String subject, String type, String mobile, int price, int month, String delYn, String regdate){
        this.no = no;
        this.subject = subject;
        this.type = type;
        this.mobile = mobile;
        this.price = price;
        this.month = month;
        this.delYn = delYn;
        this.regdate = regdate;

    }
    public void goodsUpdate(String subject,String type,String mobile,int price,int month){
        this.subject = subject;
        this.type = type;
        this.mobile = mobile;
        this.price = price;
        this.month = month;
    }
    public void goodsDelete(String delYn){
        this.delYn = delYn;
    }
}
