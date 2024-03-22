package team2.proto.dto.admin;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@ToString
public class AdminAuthDTO {

    private Integer id;
    private Integer userId;
    private boolean authYn;
    private String photoUrl;
    private LocalDateTime requestDate;
    private LocalDateTime authDate;
}