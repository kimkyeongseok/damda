package com.damda.ideaconcert.damda.comment.repository;

import com.damda.ideaconcert.damda.comment.domain.Comment;
import com.damda.ideaconcert.damda.comment.domain.QComment;
import com.damda.ideaconcert.damda.comment.dto.CommentParentRes;
import com.damda.ideaconcert.damda.comment.dto.CommentRes;
import com.damda.ideaconcert.damda.comment.dto.QCommentParentRes;
import com.damda.ideaconcert.damda.comment.dto.QCommentRes;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.damda.ideaconcert.damda.comment.domain.QComment.comment;
import static com.damda.ideaconcert.damda.user.domain.QUser.user;
import static com.damda.ideaconcert.damda.comment.domain.QCommentDeclaration.commentDeclaration;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<CommentRes> commentListByPostAll(int post_id) {
        return jpaQueryFactory.select(new QCommentRes(comment,user,
                        JPAExpressions.select(commentDeclaration.no.count())
                                .from(commentDeclaration)
                                .where(comment.no.eq(commentDeclaration.comment_id)),
                        JPAExpressions.select(commentDeclaration.no.count())
                                .from(commentDeclaration)
                                .where(comment.no.eq(commentDeclaration.comment_id),comment.user_id.eq(commentDeclaration.user_id))
                ))
                .from(comment)
                .join(user).on(user.id.eq(comment.user_id))
                .where(comment.post_id.eq(post_id),comment.level.eq(1))
                .orderBy(comment.regdate.desc())
                .fetch();
    }
    @Override
    public Comment findByIds(Integer no) {
        return jpaQueryFactory.select(new QComment(comment))
                .from(comment)
                .where(comment.no.eq(no))
                .fetchOne();
    }

    @Override
    public List<CommentParentRes> commentListByParent(int comment_id) {
        return jpaQueryFactory.select(new QCommentParentRes(comment,user,
                JPAExpressions.select(commentDeclaration.no.count())
                        .from(commentDeclaration)
                        .where(comment.no.eq(commentDeclaration.comment_id)),
                JPAExpressions.select(commentDeclaration.no.count())
                        .from(commentDeclaration)
                        .where(comment.no.eq(commentDeclaration.comment_id),comment.user_id.eq(commentDeclaration.user_id))
                ))
                .from(comment)
                .innerJoin(user).on(user.id.eq(comment.user_id))
                .where(comment.comment_id.eq(comment_id))
                .fetch();
    }

    @Override
    public Long commentByCnt(int post_id) {
        return jpaQueryFactory.select(comment.no.count())
                .from(comment)
                .where(comment.post_id.eq(post_id),comment.del_yn.eq("N"))
                .fetchOne();
    }
}
