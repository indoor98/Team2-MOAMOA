package team2.proto.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {
    private String nickname;
    private String email;
    private String password;
    private String address;
    private String school;
    private String phone;
}
