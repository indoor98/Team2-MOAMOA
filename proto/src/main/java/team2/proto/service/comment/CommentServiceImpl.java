package team2.proto.service.comment;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.dto.comment.CommentResponseDTO;
import team2.proto.entity.Comment;
import team2.proto.repository.UserRepository;
import team2.proto.repository.comment.CommentRepository;
import team2.proto.repository.post.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    
    @Override
    public List<CommentResponseDTO> commentList(Long postId) {
        return null;
    }

    @Override
    public void writeComment(CommentRequestDTO commentRequestDTO, Long postId, String email) {
        Comment comment = Comment.builder()
                .content(commentRequestDTO.getContent())
                .post(postRepository.findById(postId).get())
                .user(userRepository.findByEmail(email).get())
                .build();
        
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(CommentRequestDTO commentRequestDTO, Long commentId) {
    }

    @Override
    public void deleteComment(Long commentId) {
    }
    
}
