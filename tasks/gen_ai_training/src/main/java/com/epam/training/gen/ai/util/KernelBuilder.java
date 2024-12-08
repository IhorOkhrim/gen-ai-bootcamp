package com.epam.training.gen.ai.util;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.epam.training.gen.ai.config.ClientAzureOpenAiProperties;
import com.epam.training.gen.ai.plugin.SimplePlugin;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class KernelBuilder {

    public static final String PLUGIN_NAME = "SimplePlugin";

    private final OpenAIAsyncClient openAIAsyncClient;
    private final ClientAzureOpenAiProperties clientAzureOpenAiProperties;

    private Kernel kernel;

    public KernelBuilder withModel(String modelName) {
        kernel = createKernel(createChatCompletionService(modelName == null ? clientAzureOpenAiProperties.getDeploymentOrModelName() : modelName));
        return this;
    }

    public Kernel build() {
        return kernel;
    }

    /**
     * Creates a {@link ChatCompletionService} bean for handling chat completions using Azure OpenAI.
     *
     * @param deploymentOrModelName the Azure OpenAI deployment or model name
     * @return an instance of {@link ChatCompletionService}
     */
    private ChatCompletionService createChatCompletionService(String deploymentOrModelName) {
        return OpenAIChatCompletion.builder()
                .withModelId(deploymentOrModelName)
                .withOpenAIAsyncClient(openAIAsyncClient)
                .build();
    }

    /**
     * Creates a {@link KernelPlugin} bean using a simple plugin.
     *
     * @return an instance of {@link KernelPlugin}
     */
    private KernelPlugin createKernelPlugin() {
        return KernelPluginFactory.createFromObject(
                new SimplePlugin(), PLUGIN_NAME);
    }

    /**
     * Creates a {@link Kernel} bean to manage AI services and plugins.
     *
     * @param chatCompletionService the {@link ChatCompletionService} for handling completions
     * @return an instance of {@link Kernel}
     */
    private Kernel createKernel(ChatCompletionService chatCompletionService) {
        return Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(createKernelPlugin())
                .build();
    }

}
