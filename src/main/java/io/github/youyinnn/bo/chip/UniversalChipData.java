package io.github.youyinnn.bo.chip;

public class UniversalChipData {

    private GpsData gpsData;
    private GyroscopicData gyroscopicData;
    private AccelerometerData accelerometerData;

    public UniversalChipData() {
    }

    public UniversalChipData(GpsData gpsData, GyroscopicData gyroscopicData, AccelerometerData accelerometerData) {
        this.gpsData = gpsData;
        this.gyroscopicData = gyroscopicData;
        this.accelerometerData = accelerometerData;
    }

    public GpsData getGpsData() {
        return gpsData;
    }

    public void setGpsData(GpsData gpsData) {
        this.gpsData = gpsData;
    }

    public GyroscopicData getGyroscopicData() {
        return gyroscopicData;
    }

    public void setGyroscopicData(GyroscopicData gyroscopicData) {
        this.gyroscopicData = gyroscopicData;
    }

    public AccelerometerData getAccelerometerData() {
        return accelerometerData;
    }

    public void setAccelerometerData(AccelerometerData accelerometerData) {
        this.accelerometerData = accelerometerData;
    }
}
