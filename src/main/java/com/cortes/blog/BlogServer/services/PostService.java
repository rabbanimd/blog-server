package com.cortes.blog.BlogServer.services;

import com.cortes.blog.BlogServer.payload.PostDTO;
import com.cortes.blog.BlogServer.payload.PostPageResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostPageResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePost(Long id);
}
