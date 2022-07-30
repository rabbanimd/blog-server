package com.cortes.blogger.Controllers;

import com.cortes.blogger.Data.DTO.CommentDTO;
import com.cortes.blogger.Services.abs.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts/{post_id}/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable("post_id") Long postId) {
        return new ResponseEntity(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllComments(@PathVariable("post_id") Long postId) {
        return new ResponseEntity(commentService.fetchAllComments(postId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateComment(@PathVariable("post_id") Long postId, @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity(commentService.updateComment(postId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("post_id") Long postId, @PathVariable("comment_id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity("Deleted successfully!", HttpStatus.OK);
    }
}
