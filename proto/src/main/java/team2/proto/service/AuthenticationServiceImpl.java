package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;
import team2.proto.domain.User;
import team2.proto.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        System.out.println("여기");
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        System.out.println("====================================================");
        System.out.println(" debug >>>>>>> AuthenticationServiceImpl.signin");
        System.out.println(" parameter : "+request);
        System.out.println(" user : " + user);
        System.out.println(" jwt : " + jwt);
        System.out.println("JwtAuthenticationResponse.builder().token(jwt).build();");
        System.out.println("====================================================");
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
