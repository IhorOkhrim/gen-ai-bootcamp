package com.epam.training.gen.ai.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AWG {
    AWG_14(2.08, 13.17),  // Example: Area in mm², Resistance in ohms/km
    AWG_12(3.31, 8.23),
    AWG_10(5.26, 5.19),
    AWG_8(8.37, 3.26),
    AWG_6(13.3, 2.05),
    AWG_4(21.1, 1.29),
    AWG_2(33.6, 0.811),
    AWG_1(42.4, 0.642),
    AWG_1_0(53.5, 0.509), // 1/0
    AWG_2_0(67.4, 0.404), // 2/0
    AWG_3_0(85.0, 0.320), // 3/0
    AWG_4_0(107.2, 0.254); // 4/0

    private final double area; // in mm²
    private final double resistance; // in ohms/km
}
