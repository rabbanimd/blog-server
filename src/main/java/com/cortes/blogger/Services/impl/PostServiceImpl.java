package com.cortes.blogger.Services.impl;

import com.cortes.blogger.Data.DTO.PostDTO;
import com.cortes.blogger.Data.Models.Post;
import com.cortes.blogger.Exceptions.ResourceNotFoundException;
import com.cortes.blogger.Repositories.PostRepository;
import com.cortes.blogger.Services.abs.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postDTO.toPost();
        Post newPost = postRepository.save(post);
        PostDTO persistedPostDto = post.toPostDTO();
        return persistedPostDto;
    }

    @Override
    public List<PostDTO> getAllPost(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> allPosts = posts.getContent();
        return allPosts.stream().map(post -> post.toPostDTO()).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return post.toPostDTO();
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = postRepository.save(post);
        return updatedPost.toPostDTO();
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
