package com.sharad.springAi;

import org.springframework.ai.embedding.EmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emb")
public class EmbController {

    private EmbService embService;
    public EmbController(EmbService embService) {
        this.embService = embService;
    }

    @GetMapping("/get")
    public float[] getEmbedding(@RequestParam String text) {
        return embService.getEmbedding(text);
    }

    // multiple embeddings
    @PostMapping("/multiple")
    public List<float[]> getEmbeddings(@RequestBody List<String> texts) {
        return embService.generateEmbeddings(texts);

    }

    @PostMapping("/response")
    public EmbeddingResponse getEmbeddingsResponse(
            @RequestBody List<String> texts) {
        return embService.generateResponse(texts);
    }

    @PostMapping("/embedding")
    public Map embedForResponse(@RequestBody String text) {
        return embService.embedForResponse(text);
    }
}
