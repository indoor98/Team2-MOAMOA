package team2.proto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.proto.entity.authentication.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private Long price;
    @Column
    private Integer headCount; // 총 참여할 수 있는 인원 수
    @Column
    private LocalDateTime createDate;
    @Column(name="updated_date")
    private LocalDateTime updateDate;
    @Column
    private LocalDateTime deadline;
    @Column
    private String receivePlace;
    @Column
    private String productUrl;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

}
