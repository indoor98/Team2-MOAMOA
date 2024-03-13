package team2.proto.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;
import team2.proto.domain.RefreshToken;
import team2.proto.dto.RefreshTokenRequestDTO;
import team2.proto.dto.UserSignUpRequest;
import team2.proto.service.AuthenticationService;
import team2.proto.service.JwtService;
import team2.proto.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

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
                .map(refreshTokenService::getUserId)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getEmail());
                    return
                })
    }
}
