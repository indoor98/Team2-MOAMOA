package team2.proto.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.authentication.RefreshToken;
import team2.proto.entity.authentication.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Transactional
    Optional<RefreshToken> deleteByUserId(User user);
}
