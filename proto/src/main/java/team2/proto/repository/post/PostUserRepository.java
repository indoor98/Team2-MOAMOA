package team2.proto.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.Post;
import team2.proto.entity.PostUser;
import team2.proto.entity.User;

import java.util.List;

public interface PostUserRepository  extends JpaRepository<PostUser, Long> {
    long countByPostId(Long postId);

    boolean existsByPostIdAndUserId(Long postId, Long id);

    PostUser findByPostIdAndIsHost(Long postId, boolean b);

    void deleteByPostIdAndIsHost(Long postId, boolean b);

    List<PostUser> findByPost(Post post);
    List<PostUser> findAllByPostId(Long postId);

    PostUser findByPostAndUserAndIsHost(Post post, User user, boolean b);

}
