package com.damda.ideaconcert.damda.comment.dto;

import com.damda.ideaconcert.damda.comment.domain.Comment;
import com.damda.ideaconcert.damda.user.domain.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.yaml.snakeyaml.comments.CommentLine;

import java.util.List;

@Data
public class CommentRes {
    private Integer no;
    //피드 아이디값
    private int postId;
    //사용자 아이디값
    private int userId;
    //댓글 아이디값
    private Integer commentId;
    //사용자 닉네임
    private String nickName;
    //댓글 레벨
    private int level;
    //댓글 내용
    private String content;
    //댓글 신고 개수
    private Long commentIdDeclaration;
    //댓글 아이디별 신고 개수
    private Long userIdDeclaration;
    //프로필이미지
    private String imgUrl;
    //댓글 삭제
    private String delYn;
    //댓글 등록일짜
    private String regDate;
    //자식댓글리스트
    private List<CommentParentRes> parent;

    @QueryProjection
    public CommentRes(Comment comment, User user,Long commentIdDeclaration,Long userIdDeclaration){
        this.no = comment.getNo();
        this.postId = comment.getPost_id();
        this.userId = comment.getUser_id();
        this.level = comment.getLevel();
        this.commentId = comment.getComment_id();
        this.nickName = user.getNickName();
        this.content = comment.getContent();
        this.commentIdDeclaration = commentIdDeclaration;
        this.userIdDeclaration = userIdDeclaration;
        this.imgUrl = user.getImgUrl();
        this.delYn = comment.getDel_yn();
        this.regDate = comment.getRegdate();
    }

}
