package team2.proto.controller.mypage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.proto.dto.mypage.MyPageCommentResponseDTO;
import team2.proto.dto.mypage.MyPagePostResponseDTO;
import team2.proto.dto.mypage.MyPageUpdateRequestDTO;
import team2.proto.dto.mypage.MyPageUserResponseDTO;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.mypage.MyPageService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    // MyPageServiceImpl 객체 생성
    private final MyPageService myPageService;
    private final JwtService jwtService;

    // 회원 정보 조회
    @GetMapping("/user")
    public ResponseEntity<MyPageUserResponseDTO> showUser(HttpServletRequest request){

        System.out.println("MyPageController.findUser");
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);

        MyPageUserResponseDTO response = myPageService.findUserById(userEmail);
        try {
            System.out.println("response의 닉네임 : " + response.getNickname());
        }catch(Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 본인 계정의 닉네임 변경
    @PutMapping("/nickname")
    public ResponseEntity<Void> updateNickName(HttpServletRequest request, @RequestBody MyPageUpdateRequestDTO nickRequest){
        System.out.println("MyPageController.changeNickName");
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);

        myPageService.updateNickName(userEmail, nickRequest.getNickname());

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/postlist")
    public ResponseEntity<List<MyPagePostResponseDTO>> postByUser(HttpServletRequest request){
        System.out.println("MyPageController.postByUser");
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);

        List<MyPagePostResponseDTO> list = myPageService.findPostByUser(userEmail);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    
    @GetMapping("/commentlist")
    public ResponseEntity<List<MyPageCommentResponseDTO>> findCommentByUser(HttpServletRequest request){
        System.out.println("MyPageController.findCommentByUser");
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);

        List<MyPageCommentResponseDTO> list = myPageService.findCommentByUser(userEmail);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @GetMapping("/joinlist")
    public ResponseEntity<List<MyPagePostResponseDTO>> joinPost(HttpServletRequest request){
        System.out.println("MyPageController.findPostAll");
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);

        List<MyPagePostResponseDTO> list =  myPageService.joinPost(userEmail);
        //myPageService.findPostByUser(userEmail);
        // list - json 형식

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
