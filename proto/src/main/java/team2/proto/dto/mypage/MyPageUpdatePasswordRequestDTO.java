package team2.proto.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageUpdatePasswordRequestDTO {
    public String previousPwd;
    public String newPwd;
    public String newPwdCheck;
}
