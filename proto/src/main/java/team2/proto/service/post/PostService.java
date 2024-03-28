package team2.proto.service.post;

import jakarta.servlet.http.HttpServletRequest;
import team2.proto.dto.post.PostListResponseDTO;
import team2.proto.dto.post.PostResponseDTO;
import team2.proto.dto.post.PostUpdateDTO;
import team2.proto.dto.post.PostWriteDTO;
import team2.proto.entity.User;


import java.util.List;

public interface PostService {
    Long createPost(PostWriteDTO params, String userEmail);
    PostResponseDTO getPostById(Long id);
    void updatePost(Long id, PostUpdateDTO param);
    List<PostListResponseDTO> getAllPosts(Integer pageno);
    void delete(Long id, User user);

    void join(Long postId, HttpServletRequest request);
    void cancel(Long id);

    List<PostListResponseDTO> getAllPostsByHashtag(Integer pageNo, String hashtag);


}
