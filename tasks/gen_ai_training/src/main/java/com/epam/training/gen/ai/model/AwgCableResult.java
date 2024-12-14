package com.epam.training.gen.ai.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class AwgCableResult extends CableResult {
    public AWG awgSize;

    public AwgCableResult(AWG awg, double voltageDrop, double resistance) {
        super(voltageDrop, resistance);
        this.awgSize = awg;
    }
}
