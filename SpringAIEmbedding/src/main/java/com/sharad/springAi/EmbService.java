package com.sharad.springAi;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmbService {

    private final EmbeddingModel embeddingModel;
    public EmbService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] getEmbedding(String text) {
        return embeddingModel.embed(text);
    }

    // embedding list of data
    public List<float[]> generateEmbeddings(List<String> texts) {
        return embeddingModel.embed(texts);
    }

    // embedded response object - metadata with vector
    public EmbeddingResponse generateResponse(List<String> texts) {
        return embeddingModel.embedForResponse(texts);

    }

    public Map embedForResponse(String text) {
        EmbeddingResponse embeddingResponse = embeddingModel.embedForResponse(List.of(text));
        return Map.of("embedding", embeddingResponse);
    }
}
