package com.epam.training.gen.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PromptResponse {
    private List<String> messages;
}
