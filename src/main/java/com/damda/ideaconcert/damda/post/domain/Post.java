package com.damda.ideaconcert.damda.post.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = { "postHashTag"})
@Entity
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes ={@Index(columnList="userPk")}, name ="post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userPk;
    private String nickName;
    private String snsUrl;
    private String imgUrl;
    private String postType;
    private Integer likeCount;
    private Instant uploadDate;

    @Version
    private long version;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<PostHashTag> postHashTag;
    
    public Post(
        Integer userPk,
        String nickName,
        String snsUrl,
        String imgUrl,
        String postType
    ){
        this.userPk = userPk;
        this.nickName = nickName;
        this.snsUrl = snsUrl;
        this.imgUrl = imgUrl;
        this.postType = postType;
        this.likeCount = 0;
        this.uploadDate = Instant.now();
        this.postHashTag = new ArrayList<>();
    }

    public void addPostHashTag(PostHashTag postHashTag) {
        this.postHashTag.add(postHashTag);
        postHashTag.setPost(this);
    }
    public void update(Post updatePost){
        this.userPk = updatePost.userPk;
        this.nickName = updatePost.nickName;
        this.snsUrl = updatePost.snsUrl;
        this.postType = updatePost.postType;
    }
    public void like(Post post){
        this.likeCount = post.getLikeCount()+1;
    }
    public void unLike(Post post){
        this.likeCount = post.getLikeCount()-1;
    }
}
