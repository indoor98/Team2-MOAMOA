package team2.proto.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team2.proto.dto.authentication.WithdrawRequestDTO;
import team2.proto.entity.AdminPage;
import team2.proto.entity.User;
import team2.proto.repository.user.AdminPageRepository;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.authentication.RefreshTokenService;
import team2.proto.service.authentication.UserDetailService;
import team2.proto.service.authentication.UserService;
import team2.proto.service.storage.StorageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final StorageService storageService;
    private final JwtService jwtService;
    private final AdminPageRepository adminPageRepository;
    private final UserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;


    @PostMapping("/school_auth")
    public ResponseEntity<Void> SchoolAuth(@RequestParam(name = "image") MultipartFile imageFile, HttpServletRequest request) {
        System.out.println("debug >>>> schoolAuth MultipartFile imageFile:" + imageFile);
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token); // 토큰으로부터 유저 email을 가져오는 함수
        System.out.println(userEmail);

        // /proto/auth-images 폴더에 이미지 파일 저장합니다.
        String photoUrl = storageService.store(imageFile);
        System.out.println(photoUrl);
        // AdminPage 객체를 생성합니다.
        AdminPage adminPage = AdminPage.builder()
                .photoUrl(photoUrl)
                .user((User) userDetailService.loadUserByUsername(userEmail))
                .build();

        adminPageRepository.save(adminPage);
        return null;
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Void> SignOut(@RequestBody WithdrawRequestDTO param, HttpServletRequest request) {
        System.out.print("DEBUG >>>> UserController::SignOut");
        User user = jwtService.extractUserFromRequest(request);
        if (user == null) {
            System.out.println("현재 로그인 상태가 아닙니다");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        // 해당 유저/이메일 정보가 DB에 정상적으로 존재하는지 판별하는 spring security 함수
        // 인증 실패시 예외를 발생시킵니다.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), param.getPassword()));

        // 먼저 로그아웃 처리
        refreshTokenService.logoutRefreshToken(user.getId());
        // deleteYn -> false로 바꿔주는 service
        userService.deleteUser(user);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}



