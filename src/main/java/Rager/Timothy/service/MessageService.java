package Rager.Timothy.service;

import Rager.Timothy.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private static List<Message> messages = new ArrayList<>();

    public List<Message> getAllMessages() {
        return messages;
    }

    public List<Message> getAllMessagesByUserId(long userId) {
        ArrayList<Message> returnMe = new ArrayList<>();
        for (Message p : messages) {
            if (p.getUserId()==userId) {
                 returnMe.add(p);
            }
        }
        return returnMe;
    }

    public Message getMessage(long messageId) {
        for (Message p : messages) {
            if (p.getMessageId()==messageId) {
                return p;
            }
        }
        return null;
    }

    public boolean addMessage(Message message) {

        if (message ==null) {
            return false;
        }

        messages.add(message);
        return true;
    }

    public boolean deleteMessage(long messageId) {

        for (Message p : messages){
            if (p.getMessageId()==messageId){
                messages.remove(p);
                return true;
            }
        }

        return false;
    }

    public boolean updateMessage(long messageId, Message newMessage){
        for (Message p : messages){
            if (p.getMessageId()==messageId){
                p.setContent(newMessage.getContent());
                return true;
            }
        }
        return false;
    }

}
