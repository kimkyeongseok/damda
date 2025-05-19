package com.damda.ideaconcert.damda.comment.repository;

import com.damda.ideaconcert.damda.comment.domain.Comment;
import com.damda.ideaconcert.damda.comment.dto.CommentParentRes;
import com.damda.ideaconcert.damda.comment.dto.CommentRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {
    List<CommentRes> commentListByPostAll(int post_id);
    Comment findByIds(Integer no);
    List<CommentParentRes> commentListByParent(int comment_id);
    Long commentByCnt(int post_id);
}
