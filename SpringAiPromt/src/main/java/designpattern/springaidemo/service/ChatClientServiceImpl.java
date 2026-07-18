package designpattern.springaidemo.service;

import designpattern.springaidemo.dto.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


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

        /* PromptTemplate implementation*/
        PromptTemplate template =
                new PromptTemplate("Explain {topic}");

        Prompt prompt = template.create(Map.of(
                        "topic", question
                ));


        String answer = chatClient.prompt(prompt)
                .call()
                .content();


       /* SystemMessage systemMessage= new SystemMessage(
                "Act as a java developer having experince 2- years ");

        UserMessage userMessage= new UserMessage(question);
        Prompt prompt= new Prompt(List.of(systemMessage, userMessage));

        String answer= chatClient.prompt(prompt)
                //.user(prompt)
                .call()
                .content();*/
       long endTime = System.currentTimeMillis();
       ChatResponse chatResponse=new ChatResponse();
       chatResponse.setAns(answer);
       chatResponse.setResponseTime(endTime-startTime);
        System.out.println(answer);

       logger.info("Response Received ");
       logger.info("Execution Time : {} ms", endTime - startTime);


        return chatResponse;

    }

}



