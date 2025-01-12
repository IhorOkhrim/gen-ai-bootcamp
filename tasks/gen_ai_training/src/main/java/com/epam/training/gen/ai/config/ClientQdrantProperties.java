
package com.epam.training.gen.ai.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientQdrantProperties {

    @Value("${client.qdrant.host:localhost}")
    private String host;

    @Value("${client.qdrant.port:6334}")
    private Integer port;

    @Value("${client.qdrant.transportSecurity:false}")
    private Boolean isTransportSecurity;

    @Value("${client.qdrant.init.collection.size:1536}")
    private Integer collectionSize;

    @Value("${client.qdrant.init.collection.distance:Dot}")
    private String collectionDistance;

    @Value("${client.qdrant.init.collection.name}")
    private String collectionName;

}
