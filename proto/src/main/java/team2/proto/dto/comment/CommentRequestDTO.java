package team2.proto.dto.comment;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.proto.entity.Post;
import team2.proto.entity.authentication.User;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequestDTO {
    
    private String content;

    
}
