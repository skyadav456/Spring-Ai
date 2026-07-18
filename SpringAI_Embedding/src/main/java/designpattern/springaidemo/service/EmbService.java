package designpattern.springaidemo.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;



@Service(value = "embeddingService")
public class EmbService {
    private final EmbeddingModel embeddingModel;

    public EmbService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    // generating the embedding
    public float[] generateEmbedding(String text) {
        float[] embed = embeddingModel.embed(new Document(text));
        return embed;
    }

}
