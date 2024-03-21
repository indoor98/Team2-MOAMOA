package team2.proto.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team2.proto.entity.Post;
import team2.proto.entity.PostUser;
import team2.proto.entity.PostUserPK;

import java.util.List;

public interface MypagePostRepository extends JpaRepository<PostUser, PostUserPK> {

    @Query("SELECT pu FROM PostUser pu WHERE pu.isHost = true AND pu.user.email = :email")
    List<PostUser> findByIsHostTrue(@Param("email") String email);

}
