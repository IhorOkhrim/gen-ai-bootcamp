package com.epam.training.gen.ai.controller;

import com.epam.training.gen.ai.model.Prompt;
import com.epam.training.gen.ai.model.PromptResponse;
import com.epam.training.gen.ai.service.DialInfoService;
import com.epam.training.gen.ai.service.KernelService;
import com.epam.training.gen.ai.service.PromptService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class GenAIController {

    private final PromptService service;
    private final KernelService kernelService;
    private final DialInfoService dialInfoService;

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
}
