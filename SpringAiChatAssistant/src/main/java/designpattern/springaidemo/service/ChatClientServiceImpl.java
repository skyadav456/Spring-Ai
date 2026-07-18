package designpattern.springaidemo.service;

import designpattern.springaidemo.dto.ChatResponse;
import designpattern.springaidemo.utils.PromptTemplates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatClientServiceImpl implements ChatclientService{

    Logger logger = LoggerFactory.getLogger(ChatClientServiceImpl.class);

    private final ChatClient chatClient;
    public ChatClientServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ChatResponse askQuestion(String question) {
        long startTime = System.currentTimeMillis();

        String prompt= PromptTemplates.JAVA_ASSISTANT.formatted(question);
        String answer= chatClient.prompt()
                .user(prompt)
                .call()
                .content();
       long endTime = System.currentTimeMillis();
       ChatResponse chatResponse=new ChatResponse();
       chatResponse.setAns(answer);
        System.out.println(answer);
       chatResponse.setResponseTime(endTime-startTime);


       logger.info("Response Received ");
       logger.info("Execution Time : {} ms", endTime - startTime);

        return chatResponse;
    }
}
