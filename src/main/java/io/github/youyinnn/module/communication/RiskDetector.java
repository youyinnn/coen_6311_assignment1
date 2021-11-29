package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.GyroscopicData;

public interface RiskDetector {

    boolean isAtRisk(GyroscopicData gyroscopicData);

}
