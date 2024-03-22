package team2.proto.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.entity.User;
import team2.proto.repository.user.UserRepository;

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
