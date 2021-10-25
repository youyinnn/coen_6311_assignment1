package io.github.youyinnn.module.chip;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.ChipData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

class ChipDataProcessorImplTest {

    private static final String CHIP_NO = "qjjfapdj8810296690123";

    private final static ClassLoader classLoader = ChipData.class.getClassLoader();

    private static String gpsDataString;
    private static String gyroscopicDataString;
    private static String accelerometerDataString;

    private static final Gson gson = new Gson();
    private static final ChipDataProcessor chipDataProcessor = new ChipDataProcessorImpl();

    private static String getFileString(final String filePath) throws IOException {
        final URL url = classLoader.getResource(filePath);
        assert url != null;
        final File file = new File(url.getPath());
        return Files.readString(file.toPath());
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        gpsDataString = getFileString("data_samples/gps.data");
        gyroscopicDataString = getFileString("data_samples/gyroscopic.data");
        accelerometerDataString = getFileString("data_samples/accelerometer.data");
    }

    private void compareGpsDataJson(String rawData, GpsData gpsData) {
        final String[] part = rawData.split(" ");
        String sj = "{\"timestamp\":" +
                part[0] +
                "," +
                "\"chipNo\":\"" +
                CHIP_NO +
                "\"," +
                "\"lon\":" +
                part[1] +
                "," +
                "\"lat\":" +
                part[2] +
                "}";

        final JsonElement jsonElementFromRawData
                = JsonParser.parseString(sj);
        final JsonElement jsonElementFromProcessor
                = JsonParser.parseString(gson.toJson(gpsData));

        Assertions.assertEquals(
                jsonElementFromRawData,
                jsonElementFromProcessor
        );
    }

    private void compareAccelerometerDataJson(String rawData, AccelerometerData accelerometerData) {
        final String[] part = rawData.split(" ");
        String sj = "{\"timestamp\":" +
                part[0] +
                "," +
                "\"chipNo\":\"" +
                CHIP_NO +
                "\"," +
                "\"speed\":" +
                part[1] +
                "}";

        final JsonElement jsonElementFromRawData
                = JsonParser.parseString(sj);
        final JsonElement jsonElementFromProcessor
                = JsonParser.parseString(gson.toJson(accelerometerData));

        Assertions.assertEquals(
                jsonElementFromRawData,
                jsonElementFromProcessor
        );
    }

    private void compareGyroscopicDataJson(String rawData, GyroscopicData gyroscopicData) {
        final String[] part = rawData.split(" ");

        String sj = "{\"timestamp\":" +
                part[0] +
                "," +
                "\"chipNo\":\"" +
                CHIP_NO +
                "\"," +
                "\"x\":" +
                part[1] +
                "," +
                "\"y\":" +
                part[2] +
                "," +
                "\"z\":" +
                part[3] +
                "}";

        final JsonElement jsonElementFromRawData
                = JsonParser.parseString(sj);
        final JsonElement jsonElementFromProcessor
                = JsonParser.parseString(gson.toJson(gyroscopicData));

        Assertions.assertEquals(
                jsonElementFromRawData,
                jsonElementFromProcessor
        );
    }

    @Test
    void rawDataToGpsDataObjectTest() {

        final String[] split = gpsDataString.split(System.lineSeparator());

        for (String one : split) {
            final GpsData gpsData
                    = chipDataProcessor.rawDataToGpsDataObject(CHIP_NO, one);

            compareGpsDataJson(one, gpsData);
        }
    }

    @Test
    void rawDataToAccelerometerObject() {
        final String[] split = accelerometerDataString.split(System.lineSeparator());

        for (String one : split) {
            final AccelerometerData accelerometerData
                    = chipDataProcessor.rawDataToAccelerometerObject(CHIP_NO, one);

            compareAccelerometerDataJson(one, accelerometerData);
        }
    }

    @Test
    void rawDataToGyroscopicObject() {
        final String[] split = gyroscopicDataString.split(System.lineSeparator());

        for (String one : split) {
            final GyroscopicData gyroscopicData
                    = chipDataProcessor.rawDataToGyroscopicObject(CHIP_NO, one);

            compareGyroscopicDataJson(one, gyroscopicData);
        }


    }

    @Test
    void toOuterMessage() {
    }
}