package com.epam.training.gen.ai.controller;

import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.core.annotation.QueryParam;
import com.epam.training.gen.ai.model.EmbeddingRequest;
import com.epam.training.gen.ai.model.EmbeddingResponse;
import com.epam.training.gen.ai.model.Prompt;
import com.epam.training.gen.ai.model.PromptResponse;
import com.epam.training.gen.ai.model.QdrantResponse;
import com.epam.training.gen.ai.service.DialInfoService;
import com.epam.training.gen.ai.service.EmbeddingService;
import com.epam.training.gen.ai.service.KernelService;
import com.epam.training.gen.ai.service.PromptService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
public class GenAIController {

    private final PromptService service;
    private final KernelService kernelService;
    private final DialInfoService dialInfoService;
    private final EmbeddingService embeddingService;


    @PostMapping("/ai/semantic")
    public PromptResponse callSemanticKernel(@RequestBody @Valid Prompt request) {
        var messages = kernelService.processWithHistory(request);
        return new PromptResponse(List.of(messages));
    }

    @PostMapping("/ai/direct/open-ai")
    public PromptResponse callOpenAI(@RequestBody @Valid Prompt request) {
        var messages = service.getChatCompletions(request);
        return new PromptResponse(messages);
    }

    @GetMapping("/models")
    public List<String> getModels() {
        return dialInfoService.getModels();
    }

    @SneakyThrows
    @PostMapping("/embedding")
    public void createAndSaveEmbedding(@RequestBody EmbeddingRequest request) {
        embeddingService.processAndSaveText(request.text());
    }

    @SneakyThrows
    @PostMapping("/embeddings")
    public void createAndSaveEmbedding(@RequestBody List<EmbeddingRequest> request) {
        embeddingService.processAndSaveTextList(request);
    }
    @SneakyThrows
    @PostMapping("/embedding/testGeneration")
    public List<EmbeddingItem> createEmbedding(@RequestBody EmbeddingRequest request) {
        return embeddingService.getEmbeddings(request.text());
    }

    @SneakyThrows
    @PostMapping("/embedding/search")
    public List<EmbeddingResponse> getEmbedding(
            @RequestBody EmbeddingRequest request, @QueryParam("limit") String limit) {
        var defaultLimit = "10";
        if (limit == null || limit.isEmpty()) {
            limit = defaultLimit;
        }
        var points = embeddingService.search(request.text());
        log.info("Points: {}", points);
        return points.stream()
                .map(scoredPoint -> new EmbeddingResponse(scoredPoint.getPayloadMap().get("info").getStringValue(),
                        scoredPoint.getScore())).limit(Integer.parseInt(limit)).toList();
    }

    @SneakyThrows
    @GetMapping("/embedding/count")
    public Long getEmbeddingCount(){
        return embeddingService.getEmbeddingsQuantity();
    }

    @SneakyThrows
    @PostMapping("/embedding/collection/{collectionName}")
    public QdrantResponse createCollection(@PathVariable("collectionName") String collectionName){
        return embeddingService.createCollection(collectionName);
    }
}
