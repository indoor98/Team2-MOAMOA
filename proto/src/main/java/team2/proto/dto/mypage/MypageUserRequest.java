package team2.proto.dto.mypage;

import lombok.Data;

@Data
public class MypageUserRequest {

    // 마이페이지에서, 회원의 정보를 요청할 때, userid를 이용해서 요청함.
    private Long userid;

}
