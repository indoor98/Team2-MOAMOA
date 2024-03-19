package team2.proto.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team2.proto.dto.mypage.MypageUserRequest;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.mypage.MyPageService;

@RestController
@RequestMapping(value = "/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    // MyPageServiceImpl 객체 생성
    MyPageService myPageService;
    JwtService jwtService;

    // 회원 정보 조회
    @GetMapping("/")
    public ResponseEntity<Void> findUser(@RequestBody MypageUserRequest param ,
                                         HttpServletRequest request){

        System.out.println("MyPageController.findUser");
        String token = jwtService.extractTokenFromRequest(request);

        return null;
    }

}
