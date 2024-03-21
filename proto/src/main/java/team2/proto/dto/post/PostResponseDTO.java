package team2.proto.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponseDTO {
    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;
    private String receivePlace;
    private String productUrl;

}
