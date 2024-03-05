package team2.proto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.proto.domain.User;
import team2.proto.dto.AddUserRequestDTO;
import team2.proto.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public Long save(AddUserRequestDTO dto) {
        return userRepository.save(User.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .address(dto.getAddress())
                .school(dto.getSchool())
                .build()).getId();
    }

}
