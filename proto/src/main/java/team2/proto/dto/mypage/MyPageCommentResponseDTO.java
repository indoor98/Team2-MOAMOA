package team2.proto.dto.mypage;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import team2.proto.entity.Post;
import team2.proto.entity.authentication.User;

import java.time.LocalDateTime;

@Data
public class MyPageCommentResponseDTO {

    private String postTitle;

    private String content;

}
