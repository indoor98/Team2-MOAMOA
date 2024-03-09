package team2.proto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.proto.domain.User;
import team2.proto.dto.AddUserRequest;
import team2.proto.repository.UserRepository;
import team2.proto.service.UserServiceImpl;


@RequiredArgsConstructor
@RestController("/api")
public class UserRestApiController {

    // DIP
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public User signUp(@RequestBody AddUserRequest request) {
        Long id = userServiceImpl.save(request);
        User user = userRepository.findById(id).get();
        return user;
    }
}
