package team2.proto.service;

import team2.proto.entity.authentication.User;

import java.util.Optional;

public interface UserService {
    User findById(Long userId);
    User findByEmail(String email);
}
