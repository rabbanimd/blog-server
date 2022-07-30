package com.cortes.blogger.Services.abs;

import com.cortes.blogger.Data.DTO.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPost(int pageNo, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePost(Long id);
}
