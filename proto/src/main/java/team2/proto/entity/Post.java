package team2.proto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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

    @CreationTimestamp // 자동 생성을 위해 추가
    @Column(name = "create_date", updatable = false )
    private LocalDateTime createDate;
    @Column(name="updated_date")
    private LocalDateTime updateDate;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;
    @Column
    private String receivePlace;
    @Column
    private String productUrl;
    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;
    @Column(name = "writer")
    private String writer;


    @Builder
    public Post(String title, Long price, Integer headCount, LocalDateTime deadline, String receivePlace, String productUrl, String writer) {
        this.title = title;
        this.price = price;
        this.headCount = headCount;
        this.deadline = deadline;
        this.receivePlace = receivePlace;
        this.productUrl = productUrl;
        this.writer = writer ;
    }
}
