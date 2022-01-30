package com.cortes.blog.BlogServer.services;

import com.cortes.blog.BlogServer.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);

    void deleteComment(Long postId, Long commentId);
}
