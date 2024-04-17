package team2.proto.repository.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.RefreshToken;
import team2.proto.entity.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByUserId(User user);

    @Transactional
    Optional<RefreshToken> deleteByUserId(User user);
}
