package Rager.Timothy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SSEController {

    public static final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @CrossOrigin
    @GetMapping("/mainChat/")
    public SseEmitter mainChat(){
        SseEmitter mainChatEmitter = new SseEmitter();
        emitters.add(mainChatEmitter);
        mainChatEmitter.onCompletion(()->emitters.remove(mainChatEmitter));

        return mainChatEmitter;
    }
}
