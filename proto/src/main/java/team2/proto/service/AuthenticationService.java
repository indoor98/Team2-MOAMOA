package team2.proto.service;

import team2.proto.dao.request.SigninRequest;
import team2.proto.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SigninRequest request);
}
