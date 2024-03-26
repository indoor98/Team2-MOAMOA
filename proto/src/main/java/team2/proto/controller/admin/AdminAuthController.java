package team2.proto.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.proto.entity.AdminPage;
import team2.proto.entity.User;
import team2.proto.service.admin.AdminAuthService;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.authentication.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService adminAuthService;
    private final JwtService jwtService;
    private final UserService userService;

    // 학교인증 목록 조회
    @GetMapping("/authlist")
    public ResponseEntity<List<AdminPage>> getAllAuthList(HttpServletRequest request) {
        System.out.println("AdminAuthController.getAllAuthList");
        // 목표 : request로부터 유저 정보를 뽑는거
        // 순서 : 1. request에 담긴 토큰을 뽑는다.
        //       2. 토큰에서 유저 정보 뽑음
        //       3. 유저 정보에서 유저가 어드민인지 확인
        String token = jwtService.extractTokenFromRequest(request);
        String userEmail = jwtService.extractUserName(token);
        User user = userService.findByEmail(userEmail);

        if(!user.isAdminYn()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<AdminPage> list = adminAuthService.getAllAuthList();
        System.out.println("debug controller adminauth: " + list.get(0).toString());
        return new ResponseEntity<List<AdminPage>>(list, HttpStatus.OK);
    }

    // 학교 인증
    @PutMapping("/authlist/{authNo}")
    public ResponseEntity<Void> updateAuth(@PathVariable Long authNo, boolean approve) {
        System.out.println("AdminAuthController.updateAuth");
        adminAuthService.updateAuth(authNo, approve);
        System.out.println("UserId " + authNo + "번 인증 완료");
        return ResponseEntity.ok().build();
    }

    // 관리자 권한 부여
    @PutMapping("/adminapprove/{adminNo}")
    public ResponseEntity<Void> updateAdmin(@PathVariable Long adminNo, boolean adminApprove) {
        System.out.println("AdminAuthController.updateAdmin");
        adminAuthService.updateAdmin(adminNo, adminApprove );
        System.out.println("UserId " + adminNo + "번 관리장 승급 완료");
        return ResponseEntity.ok().build();
    }
}