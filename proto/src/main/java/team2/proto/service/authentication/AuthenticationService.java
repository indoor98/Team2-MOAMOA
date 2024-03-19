package team2.proto.service.authentication;

import team2.proto.dto.SigninRequest;
import team2.proto.dto.JwtAuthenticationResponse;
import team2.proto.dto.UserSignUpRequest;

public interface AuthenticationService {

    Long signUp(UserSignUpRequest dto);
    JwtAuthenticationResponse signIn(SigninRequest request);
}
