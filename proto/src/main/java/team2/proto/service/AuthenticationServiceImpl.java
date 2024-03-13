package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;
import team2.proto.domain.RefreshToken;
import team2.proto.domain.User;
import team2.proto.dto.UserSignUpRequest;
import team2.proto.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Long signUp(UserSignUpRequest dto) {
        return userRepository.save(User.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .school(dto.getSchool())
                .build()).getId();
    }

    @Override
    public JwtAuthenticationResponse signIn(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return JwtAuthenticationResponse.builder().
                            accessToken(jwt).
                            refreshToken(refreshToken.getRefreshToken())
                            .build();
    }
}