package team2.proto.dto.post;

import lombok.*;
import team2.proto.dto.hashtag.HashtagRequestDTO;
import team2.proto.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostWriteDTO {

    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;
    private String receivePlace;
    private String productUrl;
    private List<HashtagRequestDTO> hashtagList;


}
