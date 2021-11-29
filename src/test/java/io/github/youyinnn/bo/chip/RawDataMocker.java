package io.github.youyinnn.bo.chip;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class RawDataMocker {

    /**
     * Top latitude and longitude of Montreal.
     */
    private static final String TOP_LATITUDE = "45";
    private static final String TOP_LONGITUDE = "-73";
    private static final Random RANDOM = new Random();
    private static final int GPS_MANTISSA_LENGTH = 15;

    /**
     * Mocking the mantissa with the length of 'mantissaLength'
     */
    private static String getMantissa(final int mantissaLength) {
        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < mantissaLength; i++) {
            final int random = RANDOM.nextInt(10);
            sj.add(random + "");
        }
        return sj.toString();
    }

    private static String mockGpsData() {
        // mocking gps raw data like:
        // -73.807329854301666 45.206528409572911
        return TOP_LONGITUDE + "." + getMantissa(GPS_MANTISSA_LENGTH) + " "
                + TOP_LATITUDE + "." + getMantissa(GPS_MANTISSA_LENGTH) + (System.lineSeparator());
    }



    private static String mockAccelerometerData() {
        final int topSpeed = RANDOM.nextInt(9);
        return topSpeed + "." + getMantissa(2) + (System.lineSeparator());
    }

    private static String mockOneDimensionGyroscopicData() {
        int topDegree = RANDOM.nextInt(181);
        final boolean minus  = RANDOM.nextBoolean();
        if (minus) {
            topDegree = -topDegree;
        }
        return topDegree + "." + getMantissa(15);
    }

    public static String mockGpsDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        long currentTimeMillis = System.currentTimeMillis();
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            currentTimeMillis += RANDOM.nextInt(30 * 100);
            sj.add(String.valueOf(currentTimeMillis)); // incremental part in loop 2: adding timestamp
            sj.add(" ");
            sj.add(mockGpsData());
        }
        return sj.toString();
    }

    public static String mockAccelerometerDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        long currentTimeMillis = System.currentTimeMillis();
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            currentTimeMillis += RANDOM.nextInt(30 * 100);
            sj.add(String.valueOf(currentTimeMillis));
            sj.add(" ");
            sj.add(mockAccelerometerData());
        }
        return sj.toString();
    }

    public static String mockGyroscopicDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        long currentTimeMillis = System.currentTimeMillis();
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            currentTimeMillis += RANDOM.nextInt(30 * 100);
            sj.add(String.valueOf(currentTimeMillis));
            sj.add(" ");
            sj.add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(System.lineSeparator());
        }
        return sj.toString();
    }

    public static String[] mockUniversalDataSet(int bound) {
        AtomicInteger setSize = new AtomicInteger(RANDOM.nextInt(bound));

        if (setSize.get() == 0)
            setSize.getAndIncrement();

        long[] fixedTime = new long[setSize.get()];

        for (int i = 0; i < setSize.get(); i++) {
            if (i == 0) {
                fixedTime[i] = System.currentTimeMillis();
            } else {
                fixedTime[i] = fixedTime[i - 1] + RANDOM.nextInt(30 * 100);
            }
        }

        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < setSize.get(); i++) {
            sj.add(String.valueOf(fixedTime[i]));
            sj.add(" ");
            sj.add(mockGpsData());
        }
        StringJoiner sj2 = new StringJoiner("");
        for (int i = 0; i < setSize.get(); i++) {
            sj2.add(String.valueOf(fixedTime[i]));
            sj2.add(" ");
            sj2.add(mockAccelerometerData());
        }
        StringJoiner sj3 = new StringJoiner("");
        for (int i = 0; i < setSize.get(); i++) {
            sj3.add(String.valueOf(fixedTime[i]));
            sj3.add(" ");
            sj3.add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(System.lineSeparator());
        }
        return new String[]{sj.toString(), sj2.toString(), sj3.toString()};
    }

    @Test
    public void printMockGpsDataInput() {
        System.out.println(mockGpsDataSet(100));
    }

    @Test
    public void printMockAccelerometerDataInput() {
        System.out.println(mockAccelerometerDataSet(100));
    }

    @Test
    public void printMockGyroscopicDataInput() {
        System.out.println(mockGyroscopicDataSet(100));
    }


}
