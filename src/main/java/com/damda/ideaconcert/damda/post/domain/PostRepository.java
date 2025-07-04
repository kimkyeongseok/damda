package com.damda.ideaconcert.damda.post.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    Page<Post> findAll(Pageable Pageable);
    List<Post> findByUserPk(int userPk);
    Page<Post> findByUserPkOrderByUploadDateDesc(int userPk, Pageable pageable);
    Optional<Post> findByIdAndUserPk(int postId, int userPk);
    List<Post> findByIdIn(List<Integer> postIds);
    
}
