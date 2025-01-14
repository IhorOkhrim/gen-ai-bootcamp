package com.epam.training.gen.ai.plugin;

import com.epam.training.gen.ai.model.EmbeddingResponse;
import com.epam.training.gen.ai.service.EmbeddingService;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchPlugin {

    private final EmbeddingService embeddingService;

    @SneakyThrows
    @DefineKernelFunction(name = "search", description = "Search for a information similar to the given query.")
    public String search(
            @KernelFunctionParameter(description = "Data on which to do action", name = "query") String query,
            @KernelFunctionParameter(description = "Number of results", name = "Number of results (default = 1)") Integer results) {
        var points = embeddingService.search(query);
        log.info("Points: {}", points);
        var embeddingsResults = embeddingService.getEmbeddings(points,results);

         StringBuilder promptBuilder = new StringBuilder("Based on the following documents:\n");
            for (EmbeddingResponse result : embeddingsResults) {
                String content = result.embeddingText();
                promptBuilder.append("- ").append(content).append("\n");
            }
            promptBuilder.append("Please answer the query or provide insights. Also please add Original document");

            return promptBuilder.toString();
    }

}
