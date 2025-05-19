package com.damda.ideaconcert.damda.post.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor
@Table(name="post_hashtag")
public class PostHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;
    
    @ManyToOne(targetEntity = HashTag.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name="hashtag_id", nullable = false)
    private HashTag hashTag;

}
