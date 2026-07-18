package designpattern.springaidemo.controller;

import designpattern.springaidemo.service.EmbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/embedding")
public class EmbController {

    private EmbService embeddingService;
    public EmbController(EmbService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @GetMapping
    public float[] generateEmbedding(@RequestParam String text) {
        return embeddingService.generateEmbedding(text);
    }

}
