package team2.proto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import team2.proto.entity.authentication.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="admin_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AdminPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="photo_url")
    private String photoUrl;

    @Column(name="request_date")
    private LocalDateTime requestDate;

    @Column(name="auth_date", nullable = true)
    private LocalDateTime authDate;

    @Column(name="auth_yn")
    private Boolean authYn;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Builder
    public AdminPage(String photoUrl, User user) {
        this.photoUrl = photoUrl;
        this.requestDate = LocalDateTime.now();
        this.authDate = null;
        this.authYn = false;
        this.user = user;
    }

}
