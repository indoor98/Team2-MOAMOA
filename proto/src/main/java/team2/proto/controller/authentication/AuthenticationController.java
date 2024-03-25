package team2.proto.controller.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import team2.proto.dto.authentication.SigninDTO;
import team2.proto.dto.authentication.JwtAuthenticationResponse;
import team2.proto.entity.RefreshToken;
import team2.proto.dto.authentication.RefreshTokenRequestDTO;
import team2.proto.dto.authentication.UserSignUpDTO;
import team2.proto.entity.User;
import team2.proto.service.authentication.AuthenticationService;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.authentication.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailService;

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
}
