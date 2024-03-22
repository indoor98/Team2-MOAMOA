package team2.proto.service.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.dto.comment.CommentResponseDTO;
import team2.proto.entity.Comment;
import team2.proto.repository.user.UserRepository;
import team2.proto.repository.comment.CommentRepository;
import team2.proto.repository.post.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    
    // 게시판 번호(postId)에 해당하는 댓글들에 대한 정보 조회.
    // 댓글 조회
    @Override
    public List<CommentResponseDTO> commentList(Long postId) {
        List<Comment> commentEntityList = commentRepository.findByPostId(postId);

        List<CommentResponseDTO> commentDtoList = new ArrayList<>();

        // CommentResponseDTO : id, content, creaDate, updateDate, nickname, postId
        // Comment Entity :  id, content, creaDate, updateDate, post, user

        
        for(Comment entity : commentEntityList){
            CommentResponseDTO commentDTO = new CommentResponseDTO(
                entity.getId(),
                entity.getContent(),
                entity.getCreateDate(),
                entity.getUpdateDate(),
                entity.getUser().getNickname(),
                entity.getPost().getId()
            );

            commentDtoList.add(commentDTO);
        }
        return commentDtoList;
    }

    // 댓글 작성
    @Override
    public void writeComment(CommentRequestDTO commentRequestDTO, Long postId, String email) {
        Comment comment = Comment.builder()
                .content(commentRequestDTO.getContent())
                .post(postRepository.findById(postId).get())
                .user(userRepository.findByEmail(email).get())
                .build();
        
        commentRepository.save(comment);
    }

    // 댓글 수정
    @Override
    public void updateComment(Long postId, Long commentId, CommentRequestDTO commentRequestDTO) {
        
    }

    // 댓글 삭제
    @Override
    public void deleteComment(Long commentId) {
    }
    
}
