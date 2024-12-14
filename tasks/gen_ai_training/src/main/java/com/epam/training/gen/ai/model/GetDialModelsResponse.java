package com.epam.training.gen.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDialModelsResponse {

    private List<ModelData> data;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ModelData {
        private String model;
    }
}
