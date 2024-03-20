package team2.proto.dto.comment;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDTO {
    private Long id;
    private String comment;
     private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String nickname;
    private Long postId;
}
