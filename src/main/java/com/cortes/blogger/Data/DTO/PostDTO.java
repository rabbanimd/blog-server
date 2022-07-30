package com.cortes.blogger.Data.DTO;

import com.cortes.blogger.Data.Models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PostDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, max = 299, message = "Description should have at least 10, and at max 300 characters")
    private String description;
    @NotEmpty
    private String content;

    public Post toPost() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setDescription(this.description);
        post.setContent(this.content);
        return post;
    }
}
