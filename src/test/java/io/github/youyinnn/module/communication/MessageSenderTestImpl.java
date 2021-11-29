package io.github.youyinnn.module.communication;

import com.google.gson.Gson;
import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;

public class MessageSenderTestImpl implements MessageSender {

    private static final Gson GSON = new Gson();

    @Override
    public void sendData(String deviceId,
                                GpsData gpsData,
                                GyroscopicData gyroscopicData,
                                AccelerometerData accelerometerData) {
        System.out.println(
            "\nSend" + "message to device: " + deviceId +
            "\n\tgps data:" + GSON.toJson(gpsData) +
            "\n\tgyroscopic data:" + GSON.toJson(gyroscopicData) +
            "\n\taccelerometer data:" + GSON.toJson(accelerometerData)
        );

        System.out.println(
            "\nDevice: " + deviceId + " received message: " +
                    "\n\tgps data:" + GSON.toJson(gpsData) +
                    "\n\tgyroscopic data:" + GSON.toJson(gyroscopicData) +
                    "\n\taccelerometer data:" + GSON.toJson(accelerometerData)
        );
    }

    @Override
    public void sendState(String deviceId, BikeState state) {
        System.out.println(
                "\nSend state: " + state + "  to: " + deviceId
        );

        System.out.println(
                "\nDevice: " + deviceId + " received state: " + state
        );
    }

    @Override
    public void sendAtRiskAlert(String deviceId) {
        System.out.println(
            "\nSend risk alert to: " + deviceId
        );

        System.out.println(
                "\nDevice: " + deviceId + " received risk alert!!!"
        );
    }

}
