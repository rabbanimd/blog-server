package com.cortes.blogger.Services.impl;

import com.cortes.blogger.Data.DTO.CommentDTO;
import com.cortes.blogger.Data.Models.Comment;
import com.cortes.blogger.Data.Models.Post;
import com.cortes.blogger.Exceptions.ResourceNotFoundException;
import com.cortes.blogger.Repositories.CommentRepository;
import com.cortes.blogger.Repositories.PostRepository;
import com.cortes.blogger.Services.abs.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Comment comment = commentDTO.toComment();
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment persistedComment = commentRepository.save(comment);
        return persistedComment.toCommentDTO();
    }

    @Override
    public List<CommentDTO> fetchAllComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return post.getComments().stream().map(comment -> comment.toCommentDTO()).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//        what happens if we just update comments table do nothing for post table
        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentDTO.getId()));

        comment.setEmail(commentDTO.getEmail());
        comment.setName(commentDTO.getName());
        comment.setMessage(commentDTO.getMessage());
        Comment persistedComment = commentRepository.save(comment);
        return persistedComment.toCommentDTO();
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//        what happens if we just update comments table do nothing for post table
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        commentRepository.delete(comment);
    }
}
