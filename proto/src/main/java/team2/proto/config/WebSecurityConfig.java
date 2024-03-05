package team2.proto.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import team2.proto.service.UserDetailService;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return http.addFilterBefore(filter, CsrfFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/login", "/signup", "/user", "/swagger-ui/index.html").permitAll() // 특정 요청과 일치하는 url에 대한 엑세스 설정, permitAll -> 인증/인가 없이 접근 가능
                .anyRequest().authenticated() // -> 위 제외하고는 모두 인증/인가 필요, authenticated : 인가는 필요 x 인증은 필요
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 경로 설정
                .defaultSuccessUrl("/hello") // 로그인이 완료되었을 때 이동할 경로를 설정합니다.
                .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃이 완료되었을 때 이동할 경로
                .invalidateHttpSession(true) // 로그아웃 후 세션을 전부 삭제할지 결정
                .and()
                .csrf().disable()
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 8 사용자 정보를 가져올 서비스 설정 ?????
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
