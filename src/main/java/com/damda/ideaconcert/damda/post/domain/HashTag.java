package com.damda.ideaconcert.damda.post.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "postHashTag")
@Entity
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="hashtag")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<PostHashTag> postHashTag;
    
    public HashTag(String name) {
        this.name = name;
        this.postHashTag = new ArrayList<>();
    }
    public void addPostHashTag(PostHashTag postHashTag, HashTag hashTag) {
        this.postHashTag.add(postHashTag);
        postHashTag.setHashTag(hashTag);
    }
}
