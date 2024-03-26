package team2.proto.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.PostUser;

public interface PostUserRepository  extends JpaRepository<PostUser, Long> {
    long countByPostId(Long postId);

    boolean existsByPostIdAndUserId(Long postId, Long id);

    PostUser findByPostIdAndIsHost(Long postId, boolean b);

    void deleteByPostIdAndIsHost(Long postId, boolean b);
}
