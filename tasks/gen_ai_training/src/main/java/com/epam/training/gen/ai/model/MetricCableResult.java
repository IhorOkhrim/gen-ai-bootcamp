package com.epam.training.gen.ai.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MetricCableResult extends CableResult {
    public double area; // in mm²

    public MetricCableResult(double area, double voltageDrop, double resistance) {
        super(voltageDrop, resistance);
        this.area = area;
    }
}
