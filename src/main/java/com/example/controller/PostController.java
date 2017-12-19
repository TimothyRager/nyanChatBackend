package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @RequestMapping(value = "/posts/byUser/{userId}", method = RequestMethod.GET)
    public List<Post> getPostsByUser(@PathVariable long userId) {
        return postService.getAllPostsByUserId(userId);
    }

    @RequestMapping(value = "/posts/bySearch/{search}", method = RequestMethod.GET)
    public List<Post> getPostsBySearch(@PathVariable String search) {
        return postService.getPostsContaining(search);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity<Void> addPost(@RequestBody Post post) {
        if(postService.addPost(post)) {
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{postId}")
                    .buildAndExpand(post.getPostId())
                    .toUri();
            responseHeaders.setLocation(newPollUri);
            return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePost(@PathVariable long id, @RequestBody Post post) {
        if(postService.updatePost(id, post)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        if(postService.deletePost(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}