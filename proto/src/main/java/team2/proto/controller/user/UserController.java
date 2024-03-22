package team2.proto.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team2.proto.entity.AdminPage;
import team2.proto.entity.User;
import team2.proto.repository.user.AdminPageRepository;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.authentication.UserDetailService;
import team2.proto.service.storage.StorageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final StorageService storageService;
    private final JwtService jwtService;
    private final AdminPageRepository adminPageRepository;
    private final UserDetailService userDetailService;

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
                                .user((User)userDetailService.loadUserByUsername(userEmail))
                                .build();

        adminPageRepository.save(adminPage);
        return null;
    }
}
