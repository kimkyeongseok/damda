package com.damda.ideaconcert.damda.comment.service;

import com.damda.ideaconcert.damda.comment.domain.Comment;
import com.damda.ideaconcert.damda.comment.domain.CommentDeclaration;
import com.damda.ideaconcert.damda.comment.dto.CommentInsertReq;
import com.damda.ideaconcert.damda.comment.dto.CommentParentRes;
import com.damda.ideaconcert.damda.comment.dto.CommentRes;
import com.damda.ideaconcert.damda.comment.dto.CommentUpdateReq;
import com.damda.ideaconcert.damda.comment.repository.CommentDeclarationRepository;
import com.damda.ideaconcert.damda.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDeclarationRepository commentDeclarationRepository;

    public List<CommentRes> commentList(int postId){
        List<CommentRes> commentResList = commentRepository.commentListByPostAll(postId);

        List<CommentRes> result = new ArrayList<>();

        for (CommentRes commentRes : commentResList) {
                 List<CommentParentRes> commentParentRes = commentRepository.commentListByParent(commentRes.getNo());
                 commentRes.setParent(commentParentRes);
                 result.add(commentRes);

        }
        if(result != null){
            return result;
        }else{
            return null;
        }
    }
    @Transactional
    public void commentInsert(int userId, CommentInsertReq req){
        //현재 날짜 가져오는 부분
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        Comment comment = Comment.builder()
                .post_id(req.getPost_id())
                .user_id(userId)
                .comment_id(req.getComment_id())
                .level(req.getLevel())
                .content(req.getContent())
                .del_yn("N")
                .regdate(now_dt)
                .build();
        commentRepository.save(comment);
    }
    @Transactional
    public void commentUpdate(Integer no,CommentUpdateReq req){
        Comment updateComment = commentRepository.findByIds(no);
        if(updateComment != null){
            updateComment.CommentUpdate(req.getContent());
        }
    }
    @Transactional
    public void commentDelete(Integer no){
        Comment updateComment = commentRepository.findByIds(no);
        updateComment.CommentDelete("Y");
    }
    public void commentDeclaration(Integer no, int user_id){
        //현재 날짜 가져오는 부분
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        CommentDeclaration commentDeclaration = CommentDeclaration.builder()
                .comment_id(no)
                .user_id(user_id)
                .regdate(now_dt)
                .build();
        commentDeclarationRepository.save(commentDeclaration);
    }
    public Long commentCnt(int post_id){
        Long cnt = commentRepository.commentByCnt(post_id);
        return cnt;
    }
}
