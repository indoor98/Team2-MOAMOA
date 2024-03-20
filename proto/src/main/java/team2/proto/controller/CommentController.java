package team2.proto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.entity.Comment;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.comment.CommentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final JwtService jwtService;
    
    // 댓글 작성
    @PostMapping(value ="/{postId}")
    public String writeCommennt(@PathVariable Long postId ,  @RequestBody CommentRequestDTO commentRequestDTO, HttpServletRequest httpServletRequest) {
        System.out.println("debug >>> comment ctrl writeComment");
        String token = jwtService.extractTokenFromRequest(httpServletRequest);
        String userEmail = jwtService.extractUserName(token);
        commentService.writeComment(null, postId, userEmail);
        System.out.println(userEmail);

        return "comment write success..";
    }

    // 댓글 수정
    @PutMapping("/{postNO}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    // 댓글 삭제
    @DeleteMapping("/{postNO}")
    public void delete(){

    }
}
