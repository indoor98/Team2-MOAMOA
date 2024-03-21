package team2.proto.dto.authentication;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDTO {
    private String nickname;
    private String email;
    private String password;
    private String address;
    private String school;
    private String phone;
}
