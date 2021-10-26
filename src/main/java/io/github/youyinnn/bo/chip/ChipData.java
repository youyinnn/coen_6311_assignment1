package io.github.youyinnn.bo.chip;

/**
 * @author yinnnyou
 */
public class ChipData {

    private String chipNo;
    /**
     * Millisecond timestamp
     */
    private Long timestamp;

    public ChipData() {
    }

    public ChipData(String chipNo, Long timestamp) {
        this.chipNo = chipNo;
        this.timestamp = timestamp;
    }

    public String getChipNo() {
        return chipNo;
    }

    public void setChipNo(String chipNo) {
        this.chipNo = chipNo;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
