package team2.proto.service.authentication;

import team2.proto.entity.User;

public interface UserService {
    User findById(Long userId);
    User findByEmail(String email);

    void deleteUser(User user);
}
