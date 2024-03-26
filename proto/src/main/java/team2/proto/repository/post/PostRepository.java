package team2.proto.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team2.proto.dto.post.PostUpdateDTO;
import team2.proto.entity.Hashtag;
import team2.proto.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);


    Post findByIdAndDeleteYnFalse(Long id);


    Page<Post> findAllByDeleteYnIsFalse(Pageable pageable);

    @Query("select p from Post as p left join fetch Hashtag as h on p=h.post where h.hashtag=:hashtag")
    Page<Post> findAllByHashtag(@Param("hashtag") String hashtag, Pageable pageable);
}
