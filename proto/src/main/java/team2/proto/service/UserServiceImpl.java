package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.entity.authentication.User;
import team2.proto.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected User"));
    }
    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElse(null); // Optional 객체에서 실제 User 객체를 추출하고, 만약 값이 없으면 null 반환
    }
}
