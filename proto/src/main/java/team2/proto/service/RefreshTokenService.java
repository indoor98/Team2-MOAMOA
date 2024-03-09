package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.domain.RefreshToken;
import team2.proto.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected Token"));
    }

}
