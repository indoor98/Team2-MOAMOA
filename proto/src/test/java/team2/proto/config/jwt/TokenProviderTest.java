package team2.proto.config.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import team2.proto.domain.User;
import team2.proto.repository.UserRepository;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JWT Token 테스트
 */
@RequiredArgsConstructor
@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    // generationToken() 검증 테스트
    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken() {
        // given
        User testUser = userRepository.save(User.builder()
                .nickname("testNickName")
                .email("user@gmail.com")
                .password("test")
                .build());

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
        userRepository.deleteById(testUser.getId());
    }

    // validToken() 검증 테스트
    @DisplayName("validToken(): 만료된 토큰일 경우 유효성 검사에 실패한다.")
    @Test
    void validToken_invalidToken() {
        // Given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // When
        boolean result = tokenProvider.validToken(token);

        // Then
        assertThat(result).isFalse();
    }

    // 3. getAuthentication() 검증 테스트
    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication(){

        // Given
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // When
        Authentication authentication = tokenProvider.getAuthentication(token);

        // Then
        assertThat(((UserDetails)authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);

    }

    @DisplayName("getUserId(): 토큰으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId() {
        // Given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // When
        Long userIdByToken = tokenProvider.getUserId(token);

        // Then
        assertThat(userIdByToken).isEqualTo(userId);
    }
}
