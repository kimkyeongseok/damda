package com.damda.ideaconcert.damda.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int post_id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private Integer comment_id;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String del_yn;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public Comment(Integer no,int post_id,int user_id,Integer comment_id,int level,String content,String del_yn,String regdate){
        this.no = no;
        this.post_id = post_id;
        this.user_id = user_id;
        this.comment_id = comment_id;
        this.level = level;
        this.content = content;
        this.del_yn = del_yn;
        this.regdate = regdate;
    }
    public void CommentUpdate(String content){
        this.content = content;
    }
    public void CommentDelete(String delYn){
        this.del_yn = delYn;
    }
}
