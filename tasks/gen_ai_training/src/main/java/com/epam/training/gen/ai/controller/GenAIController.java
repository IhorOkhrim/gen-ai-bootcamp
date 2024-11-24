package com.epam.training.gen.ai.controller;

import com.epam.training.gen.ai.model.Prompt;
import com.epam.training.gen.ai.model.PromptResponse;
import com.epam.training.gen.ai.service.KernelService;
import com.epam.training.gen.ai.service.PromptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenAIController {

    private final PromptService service;
    private final KernelService kernelService;

    @PostMapping("/ai/semantic")
    public PromptResponse callSemanticKernel(@RequestBody Prompt request) {
        var messages = kernelService.processWithHistory(request.getInput());
        return new PromptResponse(List.of(messages));
    }

    @PostMapping("/ai/direct/open-ai")
    public PromptResponse callOpenAI(@RequestBody Prompt request) {
        var messages = service.getChatCompletions(request);
        return new PromptResponse(messages);
    }
}
