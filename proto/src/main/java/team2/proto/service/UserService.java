package team2.proto.service;

import team2.proto.entity.authentication.User;

public interface UserService {
    User findById(Long userId);
}
