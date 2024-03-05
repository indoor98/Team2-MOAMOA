package team2.proto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDTO {

    private String nickname;
    private String email;
    private String password;
    private String address;
    private String school;

}
