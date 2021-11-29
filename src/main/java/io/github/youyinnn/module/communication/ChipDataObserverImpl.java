package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

public class ChipDataObserverImpl implements ChipDataObserver {

    private final String chipNo;
    private final String deviceId;
    private final MessageSender messageSender;

    public String getChipNo() {
        return chipNo;
    }

    public ChipDataObserverImpl(String chipNo, String deviceId, MessageSender messageSender) {
        this.chipNo = chipNo;
        this.deviceId = deviceId;
        this.messageSender = messageSender;
    }

    @Override
    public void updateChipData(GpsData gpsData, GyroscopicData gyroscopicData,
                               AccelerometerData accelerometerData) {
        messageSender.sendData(deviceId, gpsData, gyroscopicData, accelerometerData);
    }

    @Override
    public void updateState(BikeState state) {
        if (state.equals(BikeState.AtRisk)) {
            messageSender.sendAtRiskAlert(deviceId);
        } else {
            messageSender.sendState(deviceId, state);
        }
    }
}
