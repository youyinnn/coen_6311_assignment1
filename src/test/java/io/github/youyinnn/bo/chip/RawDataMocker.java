package io.github.youyinnn.bo.chip;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.StringJoiner;

public class RawDataMocker {

    /**
     * Top latitude and longitude of Montreal.
     */
    private static final String TOP_LATITUDE = "45";
    private static final String TOP_LONGITUDE = "-73";

    private static final Random RANDOM = new Random();

    private static final int GPS_MANTISSA_LENGTH = 15;

    private static String getMantissa(final int mantissaLength) {
        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < mantissaLength; i++) {
            final int random = RANDOM.nextInt(10);
            sj.add(random + "");
        }
        return sj.toString();
    }

    private static String mockGpsData() {
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

    private static String mockGpsDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            sj.add(String.valueOf(System.currentTimeMillis()));
            sj.add(" ");
            sj.add(mockGpsData());
            try {
                Thread.sleep(RANDOM.nextInt(30 * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sj.toString();
    }

    private static String mockAccelerometerDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            sj.add(String.valueOf(System.currentTimeMillis()));
            sj.add(" ");
            sj.add(mockAccelerometerData());
            try {
                Thread.sleep(RANDOM.nextInt(30 * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sj.toString();
    }

    private static String mockGyroscopicDataSet(int bound) {
        StringJoiner sj = new StringJoiner("");
        int setSize = RANDOM.nextInt(bound);
        if (setSize == 0)
            setSize++;
        for (int i = 0; i < setSize; i++) {
            sj.add(String.valueOf(System.currentTimeMillis()));
            sj.add(" ");
            sj.add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(" ")
                    .add(mockOneDimensionGyroscopicData()).add(System.lineSeparator());
            try {
                Thread.sleep(RANDOM.nextInt(30 * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sj.toString();
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
