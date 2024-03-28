package team2.proto.controller.post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.proto.dto.post.PostListResponseDTO;
import team2.proto.dto.post.PostResponseDTO;
import team2.proto.dto.post.PostUpdateDTO;
import team2.proto.dto.post.PostWriteDTO;
import team2.proto.entity.User;
import team2.proto.service.authentication.UserService;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.post.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;
    private  final UserService userService ;

    // 게시글 작성
    @PostMapping("")
    public ResponseEntity<Void> write(@RequestBody PostWriteDTO params, HttpServletRequest request)  {
        // 토큰으로부터 가져온 email이 null이 아니라면 write 로직 실행
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);

        // 작성자 정보 설정 후 게시글 생성
        System.out.println("DEBUG >>>> PostController::write");
        postService.createPost(params, userEmail);
        System.out.println();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 단일 게시글 조회 - id
    @GetMapping("/{postno}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable("postno") Long id){
        PostResponseDTO post = postService.getPostById(id) ;
        return new ResponseEntity<PostResponseDTO>(post, HttpStatus.OK);
    }
    
    // 게시글 수정하기
    @PostMapping("/{postno}")
    public ResponseEntity<Void> update(@RequestBody PostUpdateDTO params, HttpServletRequest request, @PathVariable Long postno){
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        if(userEmail == null) {
            throw new IllegalArgumentException("로그인을 먼저 해야합니다.");
        }
        postService.updatePost(postno, params);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 게시글 전체 조회
    @GetMapping(value = "/list/{pageno}",
            produces = MediaType.APPLICATION_JSON_VALUE
        )
    // 페이징 zero based Index로 처리, 1페이지 = 0
    public ResponseEntity<List<PostListResponseDTO>> getAllPosts(@PathVariable("pageno") @Min(0) Integer pageno) throws Exception{
        List<PostListResponseDTO> posts = postService.getAllPosts(pageno);
        return new ResponseEntity<List<PostListResponseDTO>>(posts, HttpStatus.OK);
    }

    // 단일 해쉬태그로 검색하기
    @GetMapping(value = "/listhashtag/{pageno}")
    public ResponseEntity<List<PostListResponseDTO>> getPostsByHashtag(@PathVariable("pageno") @Min(0) Integer pageno, @PathParam("hashtag") String hashtag) throws Exception {
        System.out.println("DEBUG PostController::GetPostsByHashtag");
        List<PostListResponseDTO> posts = new ArrayList<PostListResponseDTO>();
        posts = postService.getAllPostsByHashtag(pageno, hashtag);
        return new ResponseEntity<List<PostListResponseDTO>>(posts, HttpStatus.OK);
    }

    // 게시글 삭제 - pathvariable
    @DeleteMapping(value = "/{postno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable("postno") Long id, HttpServletRequest request){
        User user = jwtService.extractUserFromRequest(request);
        postService.delete(id, user);
    }

    // 공동구매 참여
    @PostMapping("/join/{postno}")
    public ResponseEntity<Void> join(@PathVariable("postno") Long postId, HttpServletRequest request) {
        postService.join(postId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 공동구매 참여 취소
    @PutMapping("/cancel/{postno}")
    public ResponseEntity<String> cancelPostUser(@PathVariable("postno") Long postId) {
        postService.cancel(postId);
        return new ResponseEntity<>("PostUser 정보를 삭제했습니다.", HttpStatus.OK);
    }
}
