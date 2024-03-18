package team2.proto.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.proto.entity.Post;

@Repository
public interface
PostRepository extends JpaRepository<Post, Long> {
}
