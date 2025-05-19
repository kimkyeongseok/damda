package com.damda.ideaconcert.damda.following.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int my_id;

    @Column(nullable = false)
    private int target_id;

    @Column(nullable = false)
    private String del_yn;

    @Column(nullable = false)
    private String regdate;

    @Column(nullable = false)
    private String unfollow_date;

    @Builder
    public Following(int my_id, int target_id,String del_yn, String regdate){
        this.my_id = my_id;
        this.target_id = target_id;
        this.del_yn = del_yn;
        this.regdate = regdate;
    }
    public void unfollow(String del_yn,String unfollow_date){
        this.del_yn = del_yn;
        this.unfollow_date = unfollow_date;
    }
}
