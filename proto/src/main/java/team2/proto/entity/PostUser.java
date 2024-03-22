package team2.proto.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * post - user 간 다대다 매핑을 위한 entity
 */
@Table(name = "post_user")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class PostUser {

    @EmbeddedId
    private PostUserPK id;

    private boolean isHost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false) // 중복 매핑 수정
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false) // 중복 매핑 수정
    private User user;
}