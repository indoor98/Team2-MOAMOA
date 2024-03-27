package team2.proto.dto.admin;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminAuthDTO {

    private Long id;
    private String photoUrl;
    private LocalDateTime authDate;
    private Boolean authYn;
    private String nickname;
    private String school;
}