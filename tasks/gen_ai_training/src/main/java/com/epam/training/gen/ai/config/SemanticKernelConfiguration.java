package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.epam.training.gen.ai.plugin.SimplePlugin;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.InvocationReturnMode;
import com.microsoft.semantickernel.orchestration.PromptExecutionSettings;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@AllArgsConstructor
public class SemanticKernelConfiguration {

    private final ClientAazureOpenAiProperties clientAazureopenaiProperties;

    /**
     * Creates a {@link ChatCompletionService} bean for handling chat completions using Azure OpenAI.
     *
     * @param openAIAsyncClient the {@link OpenAIAsyncClient} to communicate with Azure OpenAI
     * @return an instance of {@link ChatCompletionService}
     */
    @Bean
    public ChatCompletionService chatCompletionService(OpenAIAsyncClient openAIAsyncClient) {
        return OpenAIChatCompletion.builder().withModelId(clientAazureopenaiProperties.getDeploymentOrModelName())
                .withOpenAIAsyncClient(openAIAsyncClient).build();
    }

    /**
     * Creates a {@link KernelPlugin} bean using a simple plugin.
     *
     * @return an instance of {@link KernelPlugin}
     */
    @Bean
    public KernelPlugin kernelPlugin() {
        return KernelPluginFactory.createFromObject(new SimplePlugin(), "SimplePlugin");
    }

    /**
     * Creates a {@link Kernel} bean to manage AI services and plugins.
     *
     * @param chatCompletionService the {@link ChatCompletionService} for handling completions
     * @param kernelPlugin          the {@link KernelPlugin} to be used in the kernel
     * @return an instance of {@link Kernel}
     */
    @Bean
    public Kernel kernel(ChatCompletionService chatCompletionService, KernelPlugin kernelPlugin) {
        return Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(kernelPlugin)
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
    @Bean
    public InvocationContext invocationContext() {
        return InvocationContext.builder()
                .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
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
    @Bean
    public PromptExecutionSettings promptExecutionSettings() {
        return PromptExecutionSettings.builder()
                .withTemperature(clientAazureopenaiProperties.getTemperature()).build();
    }

    /**
     * Creates and returns a new instance of {@link ChatHistory} with session scope.
     * <p>
     * This method is annotated with {@code @Bean}, indicating that it produces a bean to be managed by the Spring
     * container. The {@code @Scope} annotation specifies that the bean has session scope, meaning that a new instance
     * of {@link ChatHistory} will be created for each HTTP session. The {@code proxyMode} is set to
     * {@code ScopedProxyMode.TARGET_CLASS}, which means that a CGLIB-based proxy will be created for the bean, allowing
     * it to be injected into other beans while preserving the session scope.
     * </p>
     *
     * @return a new instance of {@link ChatHistory} with session scope.
     */
    @Bean
    @Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ChatHistory chatHistory() {
        return new ChatHistory();
    }
}
