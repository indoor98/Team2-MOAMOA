package team2.proto.dao.request;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    private String email;
    private String password;
}
