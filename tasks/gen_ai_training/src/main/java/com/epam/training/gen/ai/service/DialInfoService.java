package com.epam.training.gen.ai.service;

import com.epam.training.gen.ai.config.ClientAzureOpenAiProperties;
import com.epam.training.gen.ai.model.GetDialModelsResponse;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DialInfoService {
    private static final String API_URL = "https://ai-proxy.lab.epam.com/openai/deployments";
    private static final String HEADER_API_KEY = "Api-Key";

    private final RestTemplate restTemplate;
    private final ClientAzureOpenAiProperties properties;

    public List<String> getModels() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_API_KEY, properties.getOpenAiKey());

        final HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<GetDialModelsResponse> response =
                restTemplate.exchange(API_URL, HttpMethod.GET, entity, GetDialModelsResponse.class);

        return Objects.requireNonNull(response.getBody()).getData().stream().map(GetDialModelsResponse.ModelData::getModel).toList();
    }
}
