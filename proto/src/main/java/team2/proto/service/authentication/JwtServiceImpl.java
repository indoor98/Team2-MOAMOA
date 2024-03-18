package team2.proto.service.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret-key}")
    private String jwtSigningKey;

    private final JWTBlacklistService jwtBlacklistService;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        System.out.println("Debug >>>> JwtService isTokenValid");
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token) && !jwtBlacklistService.isBlacklisted(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public String createNewAccessToken(String refreshToken) {
//        //토큰 유효성 검사에 실패하면 예외 발생
//        if(!tokenProvider.validToken(refreshToken)) {
//            throw new IllegalArgumentException("Unexpected token");
//        }
//
//        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
//        User user = userServiceImpl.findById(userId);
//        return tokenProvider.generateToken(user, Duration.ofHours(2));
//    }
}
