package team2.proto.service.authentication;

import team2.proto.dto.authentication.SigninDTO;
import team2.proto.dto.authentication.JwtAuthenticationResponse;
import team2.proto.dto.authentication.UserSignUpDTO;

public interface AuthenticationService {

    Long signUp(UserSignUpDTO dto);
    JwtAuthenticationResponse signIn(SigninDTO request);
}
