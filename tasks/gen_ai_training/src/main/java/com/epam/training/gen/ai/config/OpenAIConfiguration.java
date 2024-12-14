package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up the Azure OpenAI Async Client.
 * <p>
 * This configuration defines a bean that provides an asynchronous client for interacting with the Azure OpenAI Service.
 * It uses the Azure Key Credential for authentication and connects to a specified endpoint.
 */
@Configuration
@AllArgsConstructor
public class OpenAIConfiguration {

    private final ClientAzureOpenAiProperties clientAzureOpenAiProperties;

    /**
     * Creates an {@link OpenAIAsyncClient} bean for interacting with Azure OpenAI Service asynchronously.
     *
     * @return an instance of {@link OpenAIAsyncClient}
     */
    @Bean
    public OpenAIAsyncClient openAIAsyncClient() {
        return new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(clientAzureOpenAiProperties.getOpenAiKey()))
                .endpoint(clientAzureOpenAiProperties.getOpenAiEndpoint())
                .buildAsyncClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
