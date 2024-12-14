package com.epam.training.gen.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CableResult {
    public final double cableSize; // in mm²
    public final double voltageDrop; // in volts
    public final double resistance; // in ohms
}
