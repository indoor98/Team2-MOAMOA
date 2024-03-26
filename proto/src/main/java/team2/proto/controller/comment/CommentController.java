package team2.proto.controller.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.dto.comment.CommentResponseDTO;
import team2.proto.entity.Comment;
import team2.proto.entity.User;
import team2.proto.repository.comment.CommentRepository;
import team2.proto.repository.user.UserRepository;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.comment.CommentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/comment/{postId}")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    // 회원 인증 여부 ?  필요 없음.
    // 댓글 조회
    @GetMapping("/list")
    public ResponseEntity<List<CommentResponseDTO>> commentList(@PathVariable Long postId) {
        System.out.println("debug >>> CommentList ");
        List<CommentResponseDTO> commentList = commentService.commentList(postId);

        return new ResponseEntity<List<CommentResponseDTO>>(commentList, HttpStatus.OK);
    }


    // 댓글 작성
    @PostMapping(value ="/write")
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
    // 자기 자신이 작성한 댓글에 대해서만 수정이 가능해야함
    // token에서 email을 불러온다.
    // 그 email을 가지는 userId를 가지고와서 작성자의 userId와 비교한다.
    @PutMapping("/{commentId}/update")
    public ResponseEntity<Void> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO, HttpServletRequest request) {
        System.out.println("debug >>> comment ctrl updateComment");

        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<Comment> comment = commentRepository.findById(commentId);



        // 로그인 확인
        if(userEmail == null) {
            throw new IllegalArgumentException("로그인을 먼저 해야합니다.");
        }
        else if(user.get().getId() == comment.get().getUser().getId()){

            commentService.updateComment(postId, commentId, commentRequestDTO);
        }
        else{
            throw new IllegalArgumentException("댓글 작성자가 아닙니다. 자신이 작성한 댓글만 수정 가능합니다.");

        }


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}/delete")
    public void delete(@PathVariable Long postId, @PathVariable Long commentId){

        commentService.deleteComment(postId, commentId);
    }
}