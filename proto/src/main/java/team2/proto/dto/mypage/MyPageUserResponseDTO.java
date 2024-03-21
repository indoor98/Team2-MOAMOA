package team2.proto.dto.mypage;

import lombok.Data;

@Data
public class MyPageUserResponseDTO {

    private Long id;

    private String nickname;

    private String email;

    private String address;

    private String school;

    private String phone;

}
