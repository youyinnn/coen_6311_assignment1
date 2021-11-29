package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.GyroscopicData;

import java.util.HashMap;

public class RiskDetectorTestImpl implements RiskDetector {

    private static final HashMap<String, Boolean> CHIP_IS_AT_RISK = new HashMap<>(16);

    public void assertAtRisk(GyroscopicData gyroscopicData) {
        CHIP_IS_AT_RISK.put(gyroscopicData.getChipNo(), Boolean.TRUE);
    }

    @Override
    public boolean isAtRisk(GyroscopicData gyroscopicData) {
        return CHIP_IS_AT_RISK.getOrDefault(gyroscopicData.getChipNo(), Boolean.FALSE);
    }

}
