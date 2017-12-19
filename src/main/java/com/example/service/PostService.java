package com.example.service;

import com.example.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

    private static List<Post> posts = new ArrayList<>();

    static {
            posts.add(new Post(1, 1, 1, "", "User1, Thread1, HAHAHAH"));
            posts.add(new Post(2, 2, 1, "", "User2, Thread1, What\"s so funny?"));

            Post post3 = new Post(3, 3, 1, "", "User3, Thread1, You didn\"t hear?");
            Post post4 = new Post(4,4,1,"", "User4, Thread1, Oh, about Bob?");
            Post post5 = new Post(5, 5, 1, "", "User5, Thread1, What about Bob?");
            Post post6 = new Post(6, 4, 1, "", "User4, Thread1, Bob Marley?");
            Post post7 = new Post(7, 3, 1, "", "User3, Thread1, Bob Sagget?");
            Post post8 = new Post(8, 2, 1, "", "User2, Thread1, Uncle Bob?");
            Post post9 = new Post(9, 1, 1, "", "User1, Thread1, Yo, noone talks about Uncle Bob...");
    }

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
