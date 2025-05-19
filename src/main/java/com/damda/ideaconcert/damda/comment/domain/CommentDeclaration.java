package com.damda.ideaconcert.damda.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CommentDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private int comment_id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private String regdate;

    @Builder
    public CommentDeclaration(Integer no,int comment_id,int user_id,String regdate){
        this.no =no;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.regdate = regdate;
    }
}
