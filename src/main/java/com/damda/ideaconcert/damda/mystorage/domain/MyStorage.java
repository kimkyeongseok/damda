package com.damda.ideaconcert.damda.mystorage.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MyStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public MyStorage(Integer no,int user_id,String imgUrl,String delYn,String regdate){
        this.no = no;
        this.user_id  = user_id;
        this.imgUrl = imgUrl;
        this.delYn = delYn;
        this.regdate = regdate;
    }
    public void myStorageDelete(String delYn){
        this.delYn = delYn;
    }
}
