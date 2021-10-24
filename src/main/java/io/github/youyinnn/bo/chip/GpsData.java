package io.github.youyinnn.bo.chip;

/**
 * @author yinnnyou
 */
public class GpsData extends ChipData{

    private Double lon;
    private Double lat;

    public GpsData(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public GpsData(String chipNo, Long timestamp, Double lon, Double lat) {
        super(chipNo, timestamp);
        this.lon = lon;
        this.lat = lat;
    }

    public GpsData() {
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
