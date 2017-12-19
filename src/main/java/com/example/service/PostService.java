package com.example.service;

import com.example.PostRepository;
import com.example.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll()
                .forEach(posts::add);
        return posts;
    }

    public List<Post> getAllPostsByUserId(long id) {
        List<Post> posts = new ArrayList<>();
        postRepository.findPostsByUserId(id)
                .forEach(posts::add);
        return posts;
    }

    public List<Post> getPostsContaining(String search) {
        List<Post> posts = new ArrayList<>();
        postRepository.findPostsByContentContaining(search)
                .forEach(posts::add);
        return posts;
    }

    public Post getPost(long id) {
        return postRepository.findOne(id);
    }

    public boolean addPost(Post post) {
        if(post != null) {
            postRepository.save(post);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deletePost(long id) {
        Post post = getPost(id);
        if(post != null) {
            postRepository.delete(id);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePost(long id, Post post) {
        Post oldPost = getPost(id);
        if(oldPost != null) {
            postRepository.save(post);
            return true;
        }
        else {
            return false;
        }
    }
}