package team2.proto.dto.mypage;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyPagePostResponseDTO {

    private String nickname;
    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;

}
