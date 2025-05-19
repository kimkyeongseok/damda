package com.damda.ideaconcert.damda.subscribe.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    @Column(nullable = false)
    private int user_id;
    @Column(nullable = false)
    private int payment_id;
    @Column(nullable = false)
    private int goods_id;
    @Column(nullable = false)
    private String start_date;
    @Column(nullable = false)
    private String end_date;
    @Column(nullable = false)
    private String del_yn;
    @Column(nullable = false)
    private String regdate;

    @Builder
    public Subscribe(Integer no,int user_id,int payment_id,int goods_id,String start_date,String end_date,String del_yn,String regdate){
        this.no =no;
        this.user_id =user_id;
        this.payment_id =payment_id;
        this.goods_id =goods_id;
        this.start_date =start_date;
        this.end_date =end_date;
        this.del_yn =del_yn;
        this.regdate =regdate;
    }
}
