package com.epam.training.gen.ai.plugin;

import com.epam.training.gen.ai.model.CableResult;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PowerCableCalculator {

    @DefineKernelFunction(name = "calculate_cable_size",
            description = "Calculates the appropriate cable size based on power, voltage, length, material, and allowable voltage drop.")
    public CableResult calculateCable(
            double power, double voltage, double length, String material, double powerFactor,
            double allowedVoltageDrop) {
        // Constants
        double resistivity = material.equalsIgnoreCase("copper") ? 0.0172 : 0.0282; // Ohm-mm²/m for copper and aluminum
        double maxVoltageDrop = (allowedVoltageDrop / 100) * voltage;

        // Calculate current
        double current = power / (voltage * powerFactor);

        // Iteratively find suitable cable size
        double[] standardCableSizes = {1.5, 2.5, 4, 6, 10, 16, 25, 35, 50}; // in mm²
        for (double size : standardCableSizes) {
            double resistance = (resistivity / size) * length * 2 / 1000; // Resistance in ohms
            double voltageDrop = current * resistance;

            if (voltageDrop <= maxVoltageDrop) {
                return new CableResult(size, voltageDrop, resistance);
            }
        }
        throw new IllegalArgumentException("No suitable cable size found for the given parameters.");
    }
}
