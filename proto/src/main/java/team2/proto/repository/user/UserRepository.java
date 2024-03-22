package team2.proto.repository.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team2.proto.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email로 사용자 정보를 가져옴

    @Transactional
    @Modifying
    @Query("UPDATE User U "
            + "SET U.nickname = :nickname "
            + "WHERE U.email = :email")
    void updateNickName(@Param("email") String email, @Param("nickname") String nickname);

}
