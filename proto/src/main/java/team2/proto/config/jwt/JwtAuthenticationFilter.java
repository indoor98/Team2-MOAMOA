package team2.proto.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team2.proto.service.JwtService;
import team2.proto.service.UserDetailService;
import team2.proto.service.UserService;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /*
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";
    */

    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        System.out.println("debug >>>>>>>>>>>>>>> do filter interfnal" + authHeader);
        // 토큰 없는 경우 컨텍스트 홀더에 올리지 않음
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Bearer+공백 다음부터 토큰임
        userEmail = jwtService.extractUserName(jwt); // 유저 정보 추출
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailService
                    .loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }

//
//        // 요청 헤더의 Authorizaion 키의 값 조회
//        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
//        // 가져온 값에서 접두사 제거
//        String token = getAccessToken(authorizationHeader);
//        // 가져온 토큰이 유효한지 확인하고, 유효한 경우 인증 정보 설정
//
//        if(tokenProvider.validToken(token)) {
//            Authentication authentication = tokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String getAccessToken(String authorizationHeader) {
//        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
//            return authorizationHeader.substring(TOKEN_PREFIX.length());
//        }
//            return null;
//        }
}
