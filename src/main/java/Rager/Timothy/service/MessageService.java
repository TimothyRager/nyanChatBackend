package Rager.Timothy.service;

import Rager.Timothy.controller.SSEController;
import Rager.Timothy.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessageService {

    private static List<Message> messages = new ArrayList<>();

    public void updateMainChat(Message message) {
        CopyOnWriteArrayList<SseEmitter> sseEmittersToRemove = new CopyOnWriteArrayList<>();

        SSEController.emitters.forEach(sseEmitter -> {
            try {
                sseEmitter.send(message);
            } catch (Exception e) {
                sseEmittersToRemove.add(sseEmitter);
            }
        });
        SSEController.emitters.removeAll(sseEmittersToRemove);
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public List<Message> getAllMessagesByUserId(long userId) {
        ArrayList<Message> returnMe = new ArrayList<>();
        for (Message p : messages) {
            if (p.getUserId() == userId) {
                returnMe.add(p);
            }
        }
        return returnMe;
    }

    public Message getMessage(long messageId) {
        for (Message p : messages) {
            if (p.getMessageId() == messageId) {
                return p;
            }
        }
        return null;
    }

    public boolean addMessage(Message message) {

        if (message == null) {
            return false;
        }

        messages.add(message);
        updateMainChat(message);
        return true;
    }

    public boolean deleteMessage(long messageId) {

        for (Message p : messages) {
            if (p.getMessageId() == messageId) {
                messages.remove(p);
                return true;
            }
        }

        return false;
    }

    public boolean updateMessage(long messageId, Message newMessage) {
        for (Message p : messages) {
            if (p.getMessageId() == messageId) {
                p.setContent(newMessage.getContent());
                return true;
            }
        }
        return false;
    }

}
