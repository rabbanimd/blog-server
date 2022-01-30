package com.cortes.blog.BlogServer.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 255, message = "Title length must be lie in [2, 255]")
    private String title;

    @NotEmpty
    @Size(min = 10, max = 500, message = "Description length must lie in [10, 500]")
    private String description;
    @NotEmpty
    @Size(max = 3000, message = "content size overflow. Should be less than 3000 characters")
    private String content;
    private Set<CommentDTO> comments;
}
