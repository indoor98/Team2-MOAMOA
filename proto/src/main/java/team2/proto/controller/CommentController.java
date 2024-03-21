package team2.proto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.dto.comment.CommentResponseDTO;
import team2.proto.entity.Comment;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.comment.CommentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final JwtService jwtService;

    // 회원 인증 여부 ?  필요 없음.
    // 댓글 조회
    @GetMapping("/{postId}/list")
    public ResponseEntity<List<CommentResponseDTO>> commentList(@PathVariable Long postId) {
        List<CommentResponseDTO> commentList = commentService.commentList(postId);

        return new ResponseEntity<List<CommentResponseDTO>>(commentList, HttpStatus.OK);
    }
    
    
    // 댓글 작성
    @PostMapping(value ="/{postId}/write")
    public ResponseEntity<Void> writeCommennt(@PathVariable Long postId ,  @RequestBody CommentRequestDTO commentRequestDTO, HttpServletRequest httpServletRequest) {
        System.out.println("debug >>> comment ctrl writeComment");
       
        String token = jwtService.extractTokenFromRequest(httpServletRequest);
        String userEmail = jwtService.extractUserName(token);

        // 로그인 확인
        if(userEmail == null){
            throw new IllegalArgumentException("로그인을 먼저 해야합니다.");
        }
       
        commentService.writeComment(commentRequestDTO, postId, userEmail);
        System.out.println(userEmail);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{postId}/{commentId}/update")
    public ResponseEntity<Void> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO, HttpServletRequest request) {
        System.out.println("debug >>> comment ctrl updateComment");
        
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        
        // 로그인 확인
        if(userEmail == null) {
            throw new IllegalArgumentException("로그인을 먼저 해야합니다.");
        }

        commentService.updateComment(postId, commentId, commentRequestDTO);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{postNO}")
    public void delete(){

    }
}
