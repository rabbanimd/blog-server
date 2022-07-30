package com.cortes.blogger.Controllers;

import com.cortes.blogger.Data.DTO.PostDTO;
import com.cortes.blogger.Services.abs.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<PostDTO>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity(postService.getAllPost(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable("id") Long id) {
        return new ResponseEntity(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
        return new ResponseEntity(postService.updatePost(postDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity("Post deleted successfully", HttpStatus.OK);
    }
}
