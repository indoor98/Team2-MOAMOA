package team2.proto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="comment")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class Comment {
    // id, content, creaDate, updateDate, post, user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name="create_date")
    private LocalDateTime createDate;

    @Column(name="update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", updatable = false) // 중복 매핑 수정
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false) // 중복 매핑 수정
    private User user;

    @Builder
    public Comment(String content, Post post, User user) {
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.updateDate = null;
        this.post = post;
        this.user = user;
    }
}
