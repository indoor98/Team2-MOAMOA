package team2.proto.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.entity.authentication.RefreshToken;
import team2.proto.repository.RefreshTokenRepository;
import team2.proto.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }


    // 유저 email을 사용해 refresh token을 생성하는 함수
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userRepository.findByEmail(email).get())
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .isRefreshed(false)
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

    public void logoutRefreshToken(Long userId) {
        refreshTokenRepository.deleteByUserId(userRepository.findById(userId).get());
        System.out.println("로그아웃 완료 : 유저 refreshToken 삭제");
    }
}
