package io.github.youyinnn.bo.chip;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author yinnnyou
 */
public class ChipData {

    private String chipNo;
    /**
     * Millisecond timestamp
     */
    private Long timestamp;
    private LocalDateTime localDateTime;

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

    public LocalDateTime getLocalDateTime() {
        if (localDateTime == null) {
            localDateTime = LocalDateTime.from(Instant.ofEpochMilli(timestamp));
        }
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
