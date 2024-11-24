package com.epam.training.gen.ai.examples.semantic.promt;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for generating chat completions using Azure OpenAI.
 * <p>
 * This service interacts with the Azure OpenAI API to generate chat completions
 * based on a static greeting message. It retrieves responses from the AI model
 * and logs them.
 */
@Slf4j
@Service
public class SimplePromptService {

    public static final String GREETING_MESSAGE = "Say hi";

    private final OpenAIAsyncClient aiAsyncClient;
    private final String deploymentOrModelName;

    public SimplePromptService(OpenAIAsyncClient aiAsyncClient,
                               @Value("${client-azureopenai-deployment-name}") String deploymentOrModelName) {
        this.aiAsyncClient = aiAsyncClient;
        this.deploymentOrModelName = deploymentOrModelName;
    }

    public List<String> getChatCompletions() {
        var completions = aiAsyncClient
                .getChatCompletions(
                        deploymentOrModelName,
                        new ChatCompletionsOptions(
                                List.of(new ChatRequestUserMessage(GREETING_MESSAGE))))
                .block();
        var messages = completions.getChoices().stream()
                .map(c -> c.getMessage().getContent())
                .toList();
        log.info(messages.toString());
        return messages;
    }
}
