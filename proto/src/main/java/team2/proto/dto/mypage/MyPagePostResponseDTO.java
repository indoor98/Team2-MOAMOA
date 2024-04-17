package team2.proto.dto.mypage;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyPagePostResponseDTO {

    private Long postId;
    private String nickname;
    private String title;
    private Long price;
    private Integer headCount;
    private LocalDateTime deadLine;
    private String productUrl;

    // html에서 불러온 메타 데이터를 담을 필드들
    private String metaTitle;
    private String metaImage;
    private String metaDescription;


}
