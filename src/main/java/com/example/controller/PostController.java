package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> addPost(@RequestBody Post newPost){

        boolean success = postService.addPost(newPost);

        if (!success){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{postId}").buildAndExpand(newPost.getPostId()).toUri();

        return (ResponseEntity.created(location).build());
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable long postId){
        boolean success = postService.deletePost(postId);

        if (!success){
            return ResponseEntity.notFound().build();
        }

        return (ResponseEntity.status(204).build());
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable long postId, @RequestBody Post newPost){
        boolean success = postService.updatePost(postId, newPost);

        if (!success){
            return ResponseEntity.notFound().build();
        }

        return (ResponseEntity.status(204).build());
    }

}

