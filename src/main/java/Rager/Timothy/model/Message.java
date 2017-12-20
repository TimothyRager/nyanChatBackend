package Rager.Timothy.model;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Component
public class Message {

    @Id
    private long messageId;

    private static long messageIdSeed=0;
    private long userId;
    private long threadId;
    private String timestamp;
    private String content;
    private String editedTime;
    private String userName;
    private boolean display=true;

    public Message()
    {
        messageId=messageIdSeed;
        messageIdSeed++;
    }

//    public message(String messageAsJson){
//        Gson gson = new Gson();
//        Message message = gson.fromJson(messageAsJson, Message.class);
//        this.messageId=message.getMessageId();
//        this.userId=message.getUserId();
//        this.threadId=message.getThreadId();
//        this.timestamp=message.getTimestamp();
//        this.content=message.getContent();
//    }

    public Message(long passedMessageId, long passedUserId,
                   long passedThreadId, String passedTimestamp,
                   String passedContent, String passedEditedTime,
                   String passedUserName, boolean passedDisplay){
        messageId=passedMessageId;
        userId=passedUserId;
        threadId=passedThreadId;
        timestamp=passedTimestamp;
        content=passedContent;
        editedTime=passedEditedTime;
        userName=passedUserName;
        display=passedDisplay;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
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
                "Message [messageId=%d, userId=%d, threadId=%d, timestamp=%s, content=%s]", messageId,
                userId, threadId, timestamp, content);
    }

}