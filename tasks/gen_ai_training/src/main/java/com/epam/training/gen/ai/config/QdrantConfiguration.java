package com.epam.training.gen.ai.config;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QdrantConfiguration {

    private final ClientQdrantProperties clientQdrantProperties;

    @Bean
    public QdrantClient qdrantClient() {
        return new QdrantClient(
                QdrantGrpcClient.newBuilder(
                        clientQdrantProperties.getHost(),
                        clientQdrantProperties.getPort(),
                        clientQdrantProperties.getIsTransportSecurity()).build()
        );
    }
}
