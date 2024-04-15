package team2.proto.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * post - user 간 다대다 매핑을 위한 entity
 */
@Table(name = "post_user")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Data
@Entity
public class PostUser {

    @EmbeddedId
    private PostUserPK id;

    private boolean isHost;

    private boolean emailSent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false) // 중복 매핑 수정
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false) // 중복 매핑 수정
    private User user;

    @Column(name = "delete_yn", columnDefinition = "BIT DEFAULT 0")
    private Boolean deleteYn;

    @Builder
    public PostUser(PostUserPK id, boolean isHost, /*boolean emailSent,*/ Post post, User user) {
        this.id = id ;
        this.isHost = isHost ;
        this.emailSent = emailSent;
        this.post = post ;
        this.user = user ;
    }

    // emailSent get/set 추가
    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

}