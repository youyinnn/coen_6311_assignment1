package io.github.youyinnn.bo.chip;

/**
 * @author yinnnyou
 */
public class AccelerometerData extends ChipData{

    private Double speed;

    public AccelerometerData(Double speed) {
        this.speed = speed;
    }

    public AccelerometerData(String chipNo, Long timestamp, Double speed) {
        super(chipNo, timestamp);
        this.speed = speed;
    }

    public AccelerometerData() {
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
