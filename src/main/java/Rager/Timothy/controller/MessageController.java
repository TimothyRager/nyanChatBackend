package Rager.Timothy.controller;

import Rager.Timothy.service.MessageService;
import Rager.Timothy.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable long messageId) {
        return messageService.getMessage(messageId);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> addMessage(@RequestBody Message newMessage){

        boolean success = messageService.addMessage(newMessage);

        if (!success){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{messageId}").buildAndExpand(newMessage.getMessageId()).toUri();

        return (ResponseEntity.created(location).build());
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable long messageId){
        boolean success = messageService.deleteMessage(messageId);

        if (!success){
            return ResponseEntity.notFound().build();
        }

        return (ResponseEntity.status(204).build());
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Void> updateMessage(@PathVariable long messageId, @RequestBody Message newMessage){
        boolean success = messageService.updateMessage(messageId, newMessage);

        if (!success){
            return ResponseEntity.notFound().build();
        }

        return (ResponseEntity.status(204).build());
    }

}