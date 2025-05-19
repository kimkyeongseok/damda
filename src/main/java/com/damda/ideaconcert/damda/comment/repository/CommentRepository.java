package com.damda.ideaconcert.damda.comment.repository;

import com.damda.ideaconcert.damda.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>,CommentRepositoryCustom {
}
