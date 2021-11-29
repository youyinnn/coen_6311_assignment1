package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

public interface MessageSender {

    void sendData(String deviceId,
                                GpsData gpsData,
                                GyroscopicData gyroscopicData,
                                AccelerometerData accelerometerData);

    void sendState(String deviceId, BikeState state);

    void sendAtRiskAlert(String deviceId);
}
