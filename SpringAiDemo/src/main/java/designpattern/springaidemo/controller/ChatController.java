package designpattern.springaidemo.controller;

import designpattern.springaidemo.service.ChatclientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private ChatclientService chatclientService;
    public ChatController(ChatclientService chatclientService) {
        this.chatclientService = chatclientService;
    }

    // chat?message=Explain Spring Boot
    @GetMapping
    public String chat(@RequestParam String message) {
        return chatclientService.chat(message);
    }

   /* @GetMapping
    public Flux<String> chat(@RequestParam String message) {
        return chatclientService.chat(message);
    }*/
}
