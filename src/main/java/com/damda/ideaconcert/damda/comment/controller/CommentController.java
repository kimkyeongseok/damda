package com.damda.ideaconcert.damda.comment.controller;

import com.damda.ideaconcert.damda.comment.dto.CommentInsertReq;
import com.damda.ideaconcert.damda.comment.dto.CommentRes;
import com.damda.ideaconcert.damda.comment.dto.CommentUpdateReq;
import com.damda.ideaconcert.damda.comment.service.CommentService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CommentController {
    private final CommentService commentService;
    @ApiOperation(value = "댓글리스트", notes = "피드 댓글리스트")
    @GetMapping("/comment/{post_id}")
    public ResponseEntity<List<CommentRes>> commentList(@PathVariable int post_id){
        List<CommentRes> list = commentService.commentList(post_id);
        log.info("데이터"+list);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @ApiOperation(value = "댓글등록", notes = "피드 댓글 등록")
    @PostMapping("/comment")
    public ResponseEntity<?> commentInsert(@RequestBody CommentInsertReq req, @CookieValue("_dawt") String jwt){

        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());

        commentService.commentInsert(userPk,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "댓글수정", notes = "피드 댓글 수정")
    @PatchMapping("/comment/{comment_no}")
    public ResponseEntity<?> commentUpdate(@PathVariable Integer comment_no, CommentUpdateReq req,@CookieValue("_dawt") String jwt){
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        
        commentService.commentUpdate(comment_no,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "댓글삭제", notes = "피드 댓글 삭제")
    @DeleteMapping("/comment/{comment_no}")
    public ResponseEntity<?> commentDelete(@PathVariable Integer comment_no){
        commentService.commentDelete(comment_no);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "댓글신고", notes = "피드 댓글 신고")
    @PostMapping("/comment_declaration/{comment_no}")
    public ResponseEntity<?> commentDeclaration(@PathVariable Integer comment_no, @CookieValue("_dawt") String jwt){

        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        commentService.commentDeclaration(comment_no,userPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "댓글카운트", notes = "댓글카운트")
    @GetMapping("/comment/cnt/{post_id}")
    public ResponseEntity<?> commentCnt(@PathVariable int post_id){
        Long cnt = commentService.commentCnt(post_id);
        return new ResponseEntity<>(cnt,HttpStatus.OK);
    }
}
