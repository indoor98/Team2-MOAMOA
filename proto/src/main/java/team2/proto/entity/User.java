package team2.proto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Long id;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "school")
    private String school;

    @Column(name = "phone")
    private String phone;

    @Column(name ="authentication")
    @ColumnDefault("false")
    private boolean authentication;

    @Column(name="admin_yn")
    @ColumnDefault("false")
    private boolean adminYn;

    @Builder
    public User(String nickname, String email, String password, String address, String school, String phone, String auth) { // auth?
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.school = school;
        this.phone = phone;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public String getNickname() { return nickname; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // true -> 잠금되지 않았음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true -> 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return true; // true -> 계정 사용 가능
    }


}
