package com.cortes.blog.BlogServer.controllers;

import com.cortes.blog.BlogServer.models.Post;
import com.cortes.blog.BlogServer.payload.PostDTO;
import com.cortes.blog.BlogServer.services.PostService;
import com.cortes.blog.BlogServer.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    create blog post
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

//    get all post api
    @GetMapping
    public ResponseEntity getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    )
    {
        return new ResponseEntity(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
//  Get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id")Long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

//  update post
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Long id){
        return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
    }

//    Delete post api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted Successfully", HttpStatus.OK);
    }
}
