package team2.proto.service;

import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;
import team2.proto.dto.UserSignUpRequest;

public interface AuthenticationService {

    Long signUp(UserSignUpRequest dto);
    JwtAuthenticationResponse signIn(SigninRequest request);
}
