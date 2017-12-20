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

    public Message() {
        messageId=messageIdSeed;
        messageIdSeed++;
    }

    public Message(long passedMessageId, long passedUserId, long passedThreadId, String passedTimestamp, String passedContent){
        messageId=passedMessageId;
        userId=passedUserId;
        threadId=passedThreadId;
        timestamp=passedTimestamp;
        content=passedContent;
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

    @Override
    public String toString() {
        return String.format(
                "Message [messageId=%d, userId=%d, threadId=%d, timestamp=%s, content=%s]", messageId,
                userId, threadId, timestamp, content);
    }

}