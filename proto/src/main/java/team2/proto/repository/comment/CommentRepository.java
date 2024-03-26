package team2.proto.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import team2.proto.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //    @Query("SELECT A FROM COMMENT A WHERE A.postId = :postId" )
    public List<Comment> findByPostId(@Param("postId") Long postId);

    @Transactional
    void deleteByPostIdAndId(Long postId, Long commentId);
}