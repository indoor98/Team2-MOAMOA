package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.entity.authentication.User;
import team2.proto.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected User"));
    }
}
