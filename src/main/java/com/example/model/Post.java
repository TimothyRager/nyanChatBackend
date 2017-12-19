package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class Post {

    private long postId;
    private long userId;
    private long threadId;
    private String timestamp;
    private String content;
    private String editedTime;
    private String userName;

    public Post()
    {
    }

//    public Post(String postAsJson){
//        Gson gson = new Gson();
//        Post post = gson.fromJson(postAsJson, Post.class);
//        this.postId=post.getPostId();
//        this.userId=post.getUserId();
//        this.threadId=post.getThreadId();
//        this.timestamp=post.getTimestamp();
//        this.content=post.getContent();
//    }

    public Post(long passedPostId, long passedUserId,
                long passedThreadId, String passedTimestamp,
                String passedContent){
        postId=passedPostId;
        userId=passedUserId;
        threadId=passedThreadId;
        timestamp=passedTimestamp;
        content=passedContent;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(
                "Post [postId=%d, userId=%d, threadId=%d, timestamp=%s, content=%s]", postId,
                userId, threadId, timestamp, content);
    }

}