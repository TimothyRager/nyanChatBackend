package com.example.model;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private long postId;

    private String content;
    private long threadId;
    private long userId;
    private String timestamp;

    public Post() {}

    public Post(String content, long threadId, long userId, String timestamp) {
        this.content = content;
        this.threadId = threadId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}