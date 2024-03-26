package team2.proto.controller.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team2.proto.dto.authentication.*;
import team2.proto.entity.AdminPage;
import team2.proto.entity.RefreshToken;
import team2.proto.entity.User;
import team2.proto.service.authentication.*;
import team2.proto.service.storage.StorageService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final StorageService storageService;
    private final SchoolAuthorizationService schoolAuthorizationService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserSignUpDTO params) {
        authenticationService.signUp(params);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDTO request) {
        System.out.println("DEBUG >>>> AuthenticationController::signin");
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        if (response == null) {
            System.out.println("없는 계정입니다.");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        User user = (User)userDetailService.loadUserByUsername(userEmail);
        refreshTokenService.logoutRefreshToken(user.getId());
        return ResponseEntity.ok("Logged out successfully!");
    }
    @PostMapping("/refreshtoken")
    public JwtAuthenticationResponse refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByRefreshToken(refreshTokenRequestDTO.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserId)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user);
                    return JwtAuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getRefreshToken()).build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
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

    @PostMapping("/school_auth")
    public ResponseEntity<Void> SchoolAuth(@RequestParam(name = "image") MultipartFile imageFile, HttpServletRequest request) {
        System.out.println("DEBUG >>>> AuthenticationController::SchoolAuth");
        User user = jwtService.extractUserFromRequest(request);

        // /proto/auth-images 폴더에 이미지 파일 저장합니다.
        String photoUrl = storageService.store(imageFile);

        // AdminPage 객체를 생성합니다.
        AdminPage adminPage = AdminPage.builder()
                .photoUrl(photoUrl)
                .user(user)
                .build();

        // 코드 가독성을 위해 repository를 직접 호출하는 것 대신 service 불러오기
        // adminPageRepository.save(adminPage);
        try {
            schoolAuthorizationService.save(adminPage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
