package com.damda.ideaconcert.damda.alarm.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private int target_id ;

    @Column(nullable = false)
    private int post_id ;

    @Column(nullable = false)
    private String alarm_type;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public Alarm (Integer no,int user_id,int target_id,int post_id,String alarm_type,String regdate){
        this.no = no;
        this.user_id = user_id;
        this.target_id = target_id;
        this.post_id = post_id;
        this.alarm_type = alarm_type;
        this.regdate = regdate;
    }
}
