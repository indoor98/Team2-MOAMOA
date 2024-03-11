package team2.proto.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import team2.proto.domain.User;
import team2.proto.dto.AddUserRequest;
import team2.proto.dto.UserSignUpRequest;

public interface UserService {

    Long save(UserSignUpRequest dto);
    User findById(Long userId);
}
