package team2.proto.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import team2.proto.domain.User;
import team2.proto.dto.AddUserRequest;

public interface UserService {

    Long save(AddUserRequest dto);
    User findById(Long userId);
}
