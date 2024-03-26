package team2.proto.service.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import team2.proto.entity.User;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractTokenFromRequest(HttpServletRequest request);

    User extractUserFromRequest(HttpServletRequest request);
}
