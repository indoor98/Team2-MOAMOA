package team2.proto.dto.authentication;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninDTO {
    private String email;
    private String password;
}
