package team2.proto.service.post;

import org.springframework.transaction.annotation.Transactional;
import team2.proto.dto.post.PostResponseDTO;
import team2.proto.dto.post.PostUpdateDTO;
import team2.proto.dto.post.PostWriteDTO;

import java.util.List;

public interface PostService {
    //Long createPost(PostWriteDTO params);
    Long createPost(PostWriteDTO params, String userEmail);
    PostResponseDTO getPostById(Long id);
    void updatePost(Long id, PostUpdateDTO param);
    List<PostWriteDTO> getAllPosts(Integer pageno);

}
