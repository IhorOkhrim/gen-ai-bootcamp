package com.epam.training.gen.ai.service;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.epam.training.gen.ai.config.ClientAzureOpenAiProperties;
import com.epam.training.gen.ai.model.Prompt;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PromptService {

    private final OpenAIAsyncClient aiAsyncClient;
    private final ClientAzureOpenAiProperties clientAzureOpenAiProperties;

    public List<String> getChatCompletions(Prompt prompt) {
        var chatOptions = new ChatCompletionsOptions(List.of(new ChatRequestUserMessage(prompt.getInput())));

        return aiAsyncClient.getChatCompletions(clientAzureOpenAiProperties.getDeploymentOrModelName(), chatOptions)
                .map(completions -> {
                    var messages = completions.getChoices()
                            .stream()
                            .map(c -> c.getMessage().getContent())
                            .toList();
                    log.info("Retrieved chat completions: {}", messages);
                    return messages;
                })
                .block();
    }
}
