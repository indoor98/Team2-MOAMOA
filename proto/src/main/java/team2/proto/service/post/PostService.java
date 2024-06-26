package team2.proto.service.post;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.dto.post.PostListResponseDTO;
import team2.proto.dto.post.PostResponseDTO;
import team2.proto.dto.post.PostUpdateDTO;
import team2.proto.dto.post.PostWriteDTO;
import team2.proto.entity.User;


import java.util.List;

public interface PostService {
    Long createPost(PostWriteDTO params, String userEmail);
    PostResponseDTO getPostById(Long id, String userEmail);
    void updatePost(Long id, PostUpdateDTO param);
    List<PostListResponseDTO> getAllPosts(Integer pageno);
    void delete(Long id, User user);

    void join(Long postId, HttpServletRequest request);

    // 로그인한 사용자가 게시글에 어떠한 방식으로 참여하고 있는지 리턴하는 함수
    Integer attendType(Long postId, Long userId);

    // 공동구매 참여 취소
    @Transactional
    void cancel(Long postId, HttpServletRequest request);

    List<PostListResponseDTO> getAllPostsByHashtag(Integer pageNo, String hashtag);


}
