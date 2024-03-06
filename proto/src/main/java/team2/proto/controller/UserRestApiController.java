package team2.proto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.proto.domain.User;
import team2.proto.dto.AddUserRequestDTO;
import team2.proto.repository.UserRepository;
import team2.proto.service.UserService;


@RequiredArgsConstructor
@RestController("/api")
public class UserRestApiController {

    // DIP
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public User signUp(@RequestBody AddUserRequestDTO request) {
        Long id = userService.save(request);
        User user = userRepository.findById(id).get();
        return user;
    }
}
