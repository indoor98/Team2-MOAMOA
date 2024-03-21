package team2.proto.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.proto.entity.Post;
import team2.proto.entity.authentication.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostUserDTO {
    private boolean isHost;
    private Post post;
    private User user;
}
