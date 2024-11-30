package com.epam.training.gen.ai.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientAazureOpenAiProperties {

    @Value("${client.azureopenai.key}")
    private String openAiKey;

    @Value("${client.azureopenai.endpoint}")
    private String openAiEndpoint;

    @Value("${client.azureopenai.deployment-name}")
    private String deploymentOrModelName;

     @Value("${client.azureopenai.temperature}")
     private Double temperature;
}
