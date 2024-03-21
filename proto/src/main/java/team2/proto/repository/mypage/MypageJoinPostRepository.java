package team2.proto.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.proto.entity.PostUser;
import team2.proto.entity.PostUserPK;
import java.util.List;

// 본인이 참여하는 게시글 조회
@Repository
public interface MypageJoinPostRepository extends JpaRepository<PostUser, PostUserPK> {


    List<PostUser> findByUserEmail(String email);

}
