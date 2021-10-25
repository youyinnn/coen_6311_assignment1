package io.github.youyinnn.module.chip;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

public class ChipDataProcessorImpl implements ChipDataProcessor {

    @Override
    public GpsData rawDataToGpsDataObject(String chipNo, String rawDataString) {
        final String[] part = rawDataString.split(" ");
        return new GpsData(chipNo, Long.valueOf(part[0]),
                Double.valueOf(part[1]), Double.valueOf(part[2]));
    }

    @Override
    public AccelerometerData rawDataToAccelerometerObject(String chipNo, String rawDataString) {
        final String[] part = rawDataString.split(" ");
        return new AccelerometerData(chipNo, Long.valueOf(part[0]),
                Double.valueOf(part[1]));
    }

    @Override
    public GyroscopicData rawDataToGyroscopicObject(String chipNo, String rawDataString) {
        final String[] part = rawDataString.split(" ");
        return new GyroscopicData(chipNo, Long.valueOf(part[0]),
                Double.valueOf(part[1]),
                Double.valueOf(part[2]),
                Double.valueOf(part[3]));
    }

    @Override
    public String toOuterMessage() {
        return null;
    }
}
