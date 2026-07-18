package designpattern.springaidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatclientService {

    private ChatClient chatClient;
    private ChatclientService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

  /*  public Flux<String> chat(String message) {
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content()
                .doOnNext(System.out::println); //
    }*/
}
