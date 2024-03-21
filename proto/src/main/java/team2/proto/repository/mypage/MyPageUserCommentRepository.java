package team2.proto.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team2.proto.entity.Comment;
import team2.proto.entity.authentication.User;

import java.util.List;

public interface MyPageUserCommentRepository extends JpaRepository<Comment, Long> {

        @Query("SELECT C FROM Comment C WHERE C.user.email = :email")
        List<Comment> findByEmail(String email);

}
