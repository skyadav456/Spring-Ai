package designpattern.springaidemo.controller;

import designpattern.springaidemo.dto.ChatRequest;
import designpattern.springaidemo.dto.ChatResponse;
import designpattern.springaidemo.service.ChatclientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatclientService chatclientService;
    public ChatController(ChatclientService chatclientService) {
        this.chatclientService = chatclientService;
    }


    // chat?message=Explain Spring Boot
    @PostMapping
    public ChatResponse askQuestion(@Valid @RequestBody ChatRequest request) {

        return chatclientService.askQuestion(
                request.getMessage());
    }

}
