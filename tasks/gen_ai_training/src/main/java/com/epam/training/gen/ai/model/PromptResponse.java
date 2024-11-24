package com.epam.training.gen.ai.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromptResponse {
    private List<String> messages;
}
