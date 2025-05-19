package com.damda.ideaconcert.damda.comment.repository;

import com.damda.ideaconcert.damda.comment.domain.QCommentDeclaration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.comment.domain.QCommentDeclaration.commentDeclaration;

@RequiredArgsConstructor
@Repository
public class CommentDeclarationRepositoryCustomImpl implements CommentDeclarationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Long declarationIdByCnt(int userId) {
        return jpaQueryFactory.select(commentDeclaration.no.count())
                .from(commentDeclaration)
                .where(commentDeclaration.user_id.eq(userId))
                .fetchOne();
    }
}
