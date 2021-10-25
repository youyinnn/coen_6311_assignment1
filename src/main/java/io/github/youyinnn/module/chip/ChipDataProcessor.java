package io.github.youyinnn.module.chip;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

/**
 * @author yinnnyou
 */
public interface ChipDataProcessor {

    GpsData rawDataToGpsDataObject(String chipNo, String rawDataString);

    AccelerometerData rawDataToAccelerometerObject(String chipNo, String rawDataString);

    GyroscopicData rawDataToGyroscopicObject(String chipNo, String rawDataString);

    String toOuterMessage();

}
