package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.epam.training.gen.ai.plugin.SimplePlugin;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
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
     * @param openAIAsyncClient     the {@link OpenAIAsyncClient} to communicate with Azure OpenAI
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
        return Kernel.builder().withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(kernelPlugin).build();
    }

    @Bean
    @Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ChatHistory chatHistory() {
        return new ChatHistory();
    }
}
