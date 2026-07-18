package designpattern.springaidemo.service;

import designpattern.springaidemo.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


public interface ChatclientService {

    ChatResponse askQuestion(String question);
}
