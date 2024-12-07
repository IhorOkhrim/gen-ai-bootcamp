package com.epam.training.gen.ai.service;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.epam.training.gen.ai.config.ClientAzureOpenAiProperties;
import com.epam.training.gen.ai.model.Prompt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PromptService {

    private final OpenAIAsyncClient aiAsyncClient;
    private final ClientAzureOpenAiProperties clientAazureOpenAiProperties;

    public List<String> getChatCompletions(Prompt prompt) {
        var completions = aiAsyncClient
                .getChatCompletions(
                        clientAazureOpenAiProperties.getDeploymentOrModelName(),
                        new ChatCompletionsOptions(
                                List.of(new ChatRequestUserMessage(prompt.getInput()))))
                .block();
        var messages = completions.getChoices().stream()
                .map(c -> c.getMessage().getContent())
                .toList();
        log.info(messages.toString());
        return messages;
    }

}
