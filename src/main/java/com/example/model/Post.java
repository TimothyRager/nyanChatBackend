package com.example.model;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Component
public class Post {

    @Id
    @SequenceGenerator(name="postSeq", sequenceName="Post_Sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="postSeq")
    private long postId;

    private long userId;
    private long threadId;
    private String timestamp;
    private String content;
    private String editedTime;
    private String userName;
    private boolean display=true;

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
                String passedContent, String passedEditedTime,
                String passedUserName, boolean passedDisplay){
        postId=passedPostId;
        userId=passedUserId;
        threadId=passedThreadId;
        timestamp=passedTimestamp;
        content=passedContent;
        editedTime=passedEditedTime;
        userName=passedUserName;
        display=passedDisplay;
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

    public String getEditedTime() {
        return editedTime;
    }

    public void setEditedTime(String editedTime) {
        this.editedTime = editedTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }


    @Override
    public String toString() {
        return String.format(
                "Post [postId=%d, userId=%d, threadId=%d, timestamp=%s, content=%s]", postId,
                userId, threadId, timestamp, content);
    }

}