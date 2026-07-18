package designpattern.springaidemo.service;

import designpattern.springaidemo.dto.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;


@Service
public class ChatClientServiceImpl implements ChatclientService{


    private final ChatClient chatClient;
    public ChatClientServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ChatResponse askQuestion(String question) {

        /* PromptTemplate implementation*/
       /* PromptTemplate template =
                new PromptTemplate("Explain {topic}");

        Prompt prompt = template.create(Map.of(
                        "topic", question
                ));*/


        String answer = chatClient.prompt()
                .user(question)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,"default"))
                .call()
                .chatResponse().toString();
                //.content();

       ChatResponse chatResponse=new ChatResponse();
       chatResponse.setAns(answer);
       System.out.println(chatResponse.getAns());
       return chatResponse;

    }

}



