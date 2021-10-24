package io.github.youyinnn.bo.chip;

/**
 * @author yinnnyou
 */
public class GyroscopicData extends ChipData {

    private Double x;
    private Double y;
    private Double z;

    public GyroscopicData(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GyroscopicData(String chipNo, Long timestamp, Double x, Double y, Double z) {
        super(chipNo, timestamp);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GyroscopicData() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
