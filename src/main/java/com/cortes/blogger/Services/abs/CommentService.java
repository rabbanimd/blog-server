package com.cortes.blogger.Services.abs;

import com.cortes.blogger.Data.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> fetchAllComments(Long postId);

    CommentDTO updateComment(Long postId, CommentDTO commentDTO);

    void deleteComment(Long postId, Long commentId);
}
