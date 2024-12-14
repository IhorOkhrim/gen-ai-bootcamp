package com.epam.training.gen.ai.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

@Data
public class Prompt {

    @NonNull
    @Size(min = 1, message = "Input must not be empty")
    private final String input;

    @Nullable
    @DecimalMin(value = "0.0", message = "Value must be greater than or equal to 0.0")
    @DecimalMax(value = "2.0", message = "Value must be less than or equal to 2.0")
    private Double temperature;

    private String modelName;

    private boolean functionsEnabled;

}
