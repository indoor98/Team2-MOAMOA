package team2.proto.service.comment;

import java.util.List;

import team2.proto.dto.comment.CommentRequestDTO;
import team2.proto.dto.comment.CommentResponseDTO;

public interface CommentService {
    
    // 댓글 조회
    List<CommentResponseDTO> commentList(Long postId);

    // 댓글 작성
    void writeComment(CommentRequestDTO commentRequestDTO, Long postId, String email );

    // 댓글 수정
    void updateComment(CommentRequestDTO commentRequestDTO, Long commentId );

    // 댓글 삭제
    void deleteComment(Long commentId);
}
