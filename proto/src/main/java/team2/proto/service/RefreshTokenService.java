package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.domain.RefreshToken;
import team2.proto.repository.RefreshTokenRepository;
import team2.proto.repository.UserRepository;

import java.sql.Ref;
import java.time.Instant;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected Token"));
    }


    // 유저 email을 사용해 refresh token을 생성하는 함수
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userRepository.findByEmail(email).get())
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    // RefreshToken 유효성 검사 함수
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getRefreshToken() + " : Refresh Token is expired. Please make a new login");
        } else {
            return refreshToken;
        }
    }



}
