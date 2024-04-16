package team2.proto.service.mypage;

import org.springframework.stereotype.Service;
import team2.proto.dto.mypage.MyPageCommentResponseDTO;
import team2.proto.dto.mypage.MyPagePostResponseDTO;
import team2.proto.dto.mypage.MyPageUserResponseDTO;

import java.util.List;

@Service
public interface MyPageService {


    // 내 정보 보기
    MyPageUserResponseDTO findUserById(String userEmail);

    // 본인 닉네임 변경
    void updateNickName(String userEmail, String nickName);

    // 본인 비밀번호 변경
    void updatePassword(String userEmail, String newPwd);

    // 작성한 게시글
    List<MyPagePostResponseDTO> findPostByUser(String email);

    // 참여한 게시글
    List<MyPagePostResponseDTO> joinPost(String email);

    // 작성한 댓글
    List<MyPageCommentResponseDTO> findCommentByUser(String email);

}
