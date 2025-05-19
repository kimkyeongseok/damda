package com.damda.ideaconcert.damda.comment.repository;

import com.damda.ideaconcert.damda.comment.domain.CommentDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDeclarationRepository extends JpaRepository<CommentDeclaration,Integer>,CommentDeclarationRepositoryCustom {
}
