package com.example.service;

import com.example.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

    private static List<Post> posts = new ArrayList<>();

    public List<Post> getAllPosts() {
        return posts;
    }

    public List<Post> getAllPostsByUserId(long userId) {
        ArrayList<Post> returnMe = new ArrayList<>();
        for (Post p : posts) {
            if (p.getUserId()==userId) {
                 returnMe.add(p);
            }
        }
        return returnMe;
    }

    public Post getPost(long postId) {
        for (Post p : posts) {
            if (p.getPostId()==postId) {
                return p;
            }
        }
        return null;
    }

    public boolean addPost(Post post) {

        if (post==null) {
            return false;
        }

        posts.add(post);
        return true;
    }

    public boolean deletePost(long postId) {

        for (Post p : posts){
            if (p.getPostId()==postId){
                posts.remove(p);
                return true;
            }
        }

        return false;
    }

    public boolean updatePost(long postId, Post newPost){
        for (Post p : posts){
            if (p.getPostId()==postId){
                p.setContent(newPost.getContent());
                return true;
            }
        }
        return false;
    }

}
