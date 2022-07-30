package com.cortes.blogger.Data.DTO;

import com.cortes.blogger.Data.Models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    @NotEmpty(message = "Name Should not be null or empty")
    private String name;
    @NotEmpty(message = "Please provide email")
    @Email(message = "This email is not valid.")
    private String email;
    @NotEmpty(message = "Please write comment")
    private String message;


    public Comment toComment() {
        Comment comment = new Comment();
        comment.setName(getName());
        comment.setEmail(getEmail());
        comment.setMessage(getMessage());
        return comment;
    }
}
