package team2.proto.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.proto.dto.hashtag.HashtagRequestDTO;
import team2.proto.entity.Hashtag;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponseDTO {
    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;
    private Long joinedUsersCount;
    private String receivePlace;
    private String productUrl;
    private List<HashtagRequestDTO> hashtagList;

}
