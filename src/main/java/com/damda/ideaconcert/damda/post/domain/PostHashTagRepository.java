package com.damda.ideaconcert.damda.post.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Integer> {
    PostHashTag findByPostIdAndHashTagId(int postId, int hashTagId);
    void deleteByPostId(Integer id);
    List<PostHashTag> findByPostId(int postId);
}
