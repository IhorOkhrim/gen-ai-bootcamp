package com.epam.training.gen.ai.service;

import com.epam.training.gen.ai.config.ClientAzureOpenAiProperties;
import com.epam.training.gen.ai.model.Prompt;
import com.epam.training.gen.ai.util.KernelBuilder;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.InvocationReturnMode;
import com.microsoft.semantickernel.orchestration.PromptExecutionSettings;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.semanticfunctions.KernelFunction;
import com.microsoft.semantickernel.semanticfunctions.KernelFunctionArguments;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KernelService {

    private static final String REQUEST_MESSAGE = """
            {{$chatHistory}}
            <message role="user">{{$request}}</message>""";

    private final ChatHistory chatHistory;
    private final KernelBuilder kernelBuilder;
    private final ClientAzureOpenAiProperties clientAazureopenaiProperties;


    public String processWithHistory(Prompt prompt) {
        var response = kernelBuilder
                .withModel(prompt.getModelName())
                .build()
                .invokeAsync(getChat())
                .withArguments(getKernelFunctionArguments(prompt.getInput(), chatHistory))
                .withPromptExecutionSettings(getPromptExecutionSettings(prompt.getTemperature()))
                .withInvocationContext(getInvocationContext(prompt.isFunctionsEnabled()))
                .block();

        chatHistory.addUserMessage(prompt.getInput());
        chatHistory.addAssistantMessage(Objects.requireNonNull(response).getResult());
        log.info("AI answer:{}", response.getResult());
        return response.getResult();
    }

    /**
     * Creates a kernel function for generating a chat response using a predefined prompt template.
     * <p>
     * The template includes the chat history and the user's message as variables.
     *
     * @return a {@link KernelFunction} for handling chat-based AI interactions
     */
    private KernelFunction<String> getChat() {
        return KernelFunction.<String>createFromPrompt(REQUEST_MESSAGE).build();
    }

    /**
     * Creates the kernel function arguments with the user prompt and chat history.
     *
     * @param prompt      the user's input
     * @param chatHistory the current chat history
     * @return a {@link KernelFunctionArguments} instance containing the variables for the AI model
     */
    private KernelFunctionArguments getKernelFunctionArguments(String prompt, ChatHistory chatHistory) {
        return KernelFunctionArguments.builder()
                .withVariable("request", prompt)
                .withVariable("chatHistory", chatHistory)
                .build();
    }

    /**
     * Configures and provides a {@link PromptExecutionSettings} bean.
     *
     * <p>This method creates an instance of {@link PromptExecutionSettings} using a builder pattern.
     * The resulting {@link PromptExecutionSettings} instance is then exposed as a Spring Bean, allowing it to be
     * autowired into other components within the Spring application context.</p>
     *
     * @return a configured {@link PromptExecutionSettings} instance
     */
    private PromptExecutionSettings getPromptExecutionSettings(Double temperature) {
        log.info("Temperature: {}", temperature);
        return PromptExecutionSettings.builder()
                .withTemperature(temperature == null ? clientAazureopenaiProperties.getTemperature() : temperature)
                .build();
    }

    /**
     * Creates and configures an {@link InvocationContext} bean.
     *
     * <p>The {@code InvocationContext} is set up with the following configurations:</p>
     * <ul>
     *   <li>Invocation return mode is set to {@link InvocationReturnMode#LAST_MESSAGE_ONLY LAST_MESSAGE_ONLY}</li>
     *   <li>Tool call behavior is set to allow all kernel functions, as defined by {@link ToolCallBehavior#allowAllKernelFunctions(boolean) allowAllKernelFunctions(true)}</li>
     * </ul>
     *
     * @return an instance of {@link InvocationContext} with the specified configurations
     */
    private InvocationContext getInvocationContext(boolean functionsEnabled) {
        return InvocationContext.builder()
                .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                .withToolCallBehavior(functionsEnabled ? ToolCallBehavior.allowAllKernelFunctions(true) : null)
                .build();
    }

}
