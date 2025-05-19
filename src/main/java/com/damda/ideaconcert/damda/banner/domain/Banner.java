package com.damda.ideaconcert.damda.banner.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private String banner_type;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int old;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)
    private String banner_img_url;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public Banner(Integer no,int user_id,String banner_type,String gender,String email,
                  int old,String area,String profession,String banner_img_url,String regdate){
        this.no = no;
        this.user_id = user_id;
        this.banner_type = banner_type;
        this.gender = gender;
        this.email = email;
        this.old = old;
        this.area = area;
        this.profession = profession;
        this.banner_img_url = banner_img_url;
        this.regdate = regdate;

    }
}
