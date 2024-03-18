package team2.proto.dto.post;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostWriteDTO {

    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;;
    private String receivePlace;
    private String productUrl;

}
