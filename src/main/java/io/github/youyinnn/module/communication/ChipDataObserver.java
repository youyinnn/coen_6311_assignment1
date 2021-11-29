package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

/**
 * @author yinnnyou
 */
public interface ChipDataObserver {

    void updateChipData(GpsData gpsData, GyroscopicData gyroscopicData,
                        AccelerometerData accelerometerData);

    void updateState(BikeState state);

}
