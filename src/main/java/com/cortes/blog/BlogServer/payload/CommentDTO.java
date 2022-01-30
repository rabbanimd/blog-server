package com.cortes.blog.BlogServer.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDTO {
    private Long id;

    @NotEmpty
    @Size(max = 255, message = "Name should Not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 2,max = 1000, message = "Body should not be empty or greater than 1000 characters")
    private String body;
}
