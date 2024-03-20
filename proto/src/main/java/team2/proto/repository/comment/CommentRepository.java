package team2.proto.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import team2.proto.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    
}
