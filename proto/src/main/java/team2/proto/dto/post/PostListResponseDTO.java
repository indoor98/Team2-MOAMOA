package team2.proto.dto.post;

import lombok.*;
import team2.proto.dto.hashtag.HashtagRequestDTO;
import team2.proto.entity.Hashtag;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListResponseDTO {

    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;
    private String receivePlace;
    private String productUrl;
    private List<HashtagRequestDTO> hastagList;

    // 게시글 클릭 시 라우팅 연결을 위한 id 필드 생성.......................ㅎ
    private Long id;
    private String metaImage;

}

