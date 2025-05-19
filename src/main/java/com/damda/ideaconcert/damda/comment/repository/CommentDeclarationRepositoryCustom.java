package com.damda.ideaconcert.damda.comment.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentDeclarationRepositoryCustom {
    Long declarationIdByCnt(int userId);
}
