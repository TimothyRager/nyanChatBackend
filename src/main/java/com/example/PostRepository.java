package com.example;

import com.example.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findPostsByUserId(long id);
    Iterable<Post> findPostsByContentContaining(String search);
}