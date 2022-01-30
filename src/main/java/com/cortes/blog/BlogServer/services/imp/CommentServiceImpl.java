package com.cortes.blog.BlogServer.services.imp;


import com.cortes.blog.BlogServer.exceptions.BlogAPIException;
import com.cortes.blog.BlogServer.exceptions.ResourceNotFoundException;
import com.cortes.blog.BlogServer.models.Comment;
import com.cortes.blog.BlogServer.models.Post;
import com.cortes.blog.BlogServer.payload.CommentDTO;
import com.cortes.blog.BlogServer.repositories.CommentRepository;
import com.cortes.blog.BlogServer.repositories.PostRepository;
import com.cortes.blog.BlogServer.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO){
        Comment comment = toComment(commentDTO);

//        retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
//        set post to comment entity
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);
        return toCommentDTO(saved);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> toCommentDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
//        retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }
        return toCommentDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment savedComment = commentRepository.save(comment);
        return toCommentDTO(savedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        commentRepository.delete(comment);
    }

    private CommentDTO toCommentDTO(Comment comment){
        CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setBody(comment.getBody());
//        commentDTO.setId(comment.getId());
//        commentDTO.setEmail(comment.getEmail());
//        commentDTO.setName(comment.getName());
        return commentDTO;
    }
    private Comment toComment(CommentDTO commentDTO){
        Comment comment = mapper.map(commentDTO, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDTO.getId());
//        comment.setBody(commentDTO.getBody());
//        comment.setEmail(commentDTO.getEmail());
//        comment.setName(commentDTO.getName());
        return comment;
    }
}
