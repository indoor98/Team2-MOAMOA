package team2.proto.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;
import team2.proto.domain.RefreshToken;
import team2.proto.dto.RefreshTokenRequestDTO;
import team2.proto.dto.UserSignUpRequest;
import team2.proto.service.AuthenticationService;
import team2.proto.service.JWTBlacklistService;
import team2.proto.service.JwtService;
import team2.proto.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final JWTBlacklistService jwtBlacklistService;

    @PostMapping("signup")
    public ResponseEntity<Void> signup(@RequestBody UserSignUpRequest params) {
        authenticationService.signUp(params);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(authenticationService.signIn(request));
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

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        jwtBlacklistService.addToBlacklist(token);
        System.out.println("logout 완료");
        return ResponseEntity.ok("Logged out successfully!");
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        // Get the Authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the Authorization header is not null and starts with "Bearer "
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            // Extract the JWT token (remove "Bearer " prefix)
            return authorizationHeader.substring(7);
        }

        // If the Authorization header is not valid, return null
        return null;
    }
}
