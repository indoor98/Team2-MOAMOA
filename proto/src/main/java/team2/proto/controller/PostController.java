package team2.proto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.proto.dto.post.PostWriteDTO;
import team2.proto.service.post.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Void> write(@RequestBody PostWriteDTO params) {
        // 코드 작성 부분
        return null;
    }
}
