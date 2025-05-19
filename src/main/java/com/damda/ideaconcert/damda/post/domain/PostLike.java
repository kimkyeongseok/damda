package com.damda.ideaconcert.damda.post.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "userPk"), @Index(columnList = "postId")}, name="post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer postId;
    private Integer userPk;
    private Instant likedDate;
    
    public PostLike(
        Integer postId,
        Integer userPk
    ){
        this.postId = postId;
        this.userPk = userPk;
        this.likedDate = Instant.now();
    }
}
