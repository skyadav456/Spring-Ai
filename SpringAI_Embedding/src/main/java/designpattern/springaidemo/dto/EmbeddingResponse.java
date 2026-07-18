package designpattern.springaidemo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingResponse {

    private String text;
    private int dimensions;
    private List<Float> embedding;
}
