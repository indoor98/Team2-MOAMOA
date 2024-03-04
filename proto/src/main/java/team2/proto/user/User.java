package team2.proto.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

public class User {
    // Spring Security User 상속받아 커스텀할 예정
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private boolean authentication;

}
