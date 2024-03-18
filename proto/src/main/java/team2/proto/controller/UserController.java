package team2.proto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/school_auth")
    public ResponseEntity<Void> SchoolAuth( ) {
        // 파일 업로드 공부 후 기능 생성하기
        return null;
    }
}
