package com.cortes.blog.BlogServer.services.imp;

import com.cortes.blog.BlogServer.exceptions.ResourceNotFoundException;
import com.cortes.blog.BlogServer.models.Post;
import com.cortes.blog.BlogServer.payload.PostDTO;
import com.cortes.blog.BlogServer.payload.PostPageResponse;
import com.cortes.blog.BlogServer.repositories.PostRepository;
import com.cortes.blog.BlogServer.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper mapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post savedPost = postRepository.save(toPost(postDTO));
        return toPostDTO(savedPost);
    }

    @Override
    public PostPageResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
//        get content from page object
        List<Post> postsList = posts.getContent();
        List<PostDTO> content = postsList.stream().map(post -> toPostDTO(post)).collect(Collectors.toList());
        PostPageResponse postPageResponse = new PostPageResponse();
        postPageResponse.setContent(content);
        postPageResponse.setPageNo(posts.getNumber());
        postPageResponse.setPageSize(posts.getSize());
        postPageResponse.setTotalElements(posts.getTotalElements());
        postPageResponse.setTotalPages(posts.getTotalPages());
        postPageResponse.setLast(posts.isLast());
        return postPageResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return toPostDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post saved = postRepository.save(post);
        return toPostDTO(saved);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    private PostDTO toPostDTO(Post post){
        PostDTO postDTO = mapper.map(post, PostDTO.class);
//        PostDTO postResponse = new PostDTO();
//        postResponse.setId(post.getId());
//        postResponse.setContent(post.getContent());
//        postResponse.setDescription(post.getDescription());
//        postResponse.setTitle(post.getTitle());
        return postDTO;
    }

    private Post toPost(PostDTO postDTO){
        Post post = mapper.map(postDTO, Post.class);
//        Post post = new Post();
//        post.setTitle(postDTO.getTitle());
//        post.setDescription(postDTO.getDescription());
//        post.setContent(postDTO.getContent());
        return post;
    }
}
