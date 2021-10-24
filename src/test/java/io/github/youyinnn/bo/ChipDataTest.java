package io.github.youyinnn.bo;

import com.google.gson.Gson;
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

class ChipDataTest {

    private final static ClassLoader classLoader = ChipData.class.getClassLoader();

    private static String gpsJson;
    private static String gyroscopicJson;
    private static String accelerometerJson;

    private final static Gson GSON = new Gson();

    @BeforeAll
    public static void beforeAll() throws IOException {
        gpsJson = getJsonString("data_samples/gps.data.json");
        gyroscopicJson = getJsonString("data_samples/gyroscopic.data.json");
        accelerometerJson = getJsonString("data_samples/accelerometer.data.json");
    }

    private static String getJsonString(final String filePath) throws IOException {
        final URL url = classLoader.getResource(filePath);
        assert url != null;
        final File file = new File(url.getPath());
        return Files.readString(file.toPath());
    }

    @Test
    public void testJsonParse() {
        final GpsData gpsData = GSON.fromJson(gpsJson, GpsData.class);
        final String gpsDataJson = GSON.toJson(gpsData);
        final GyroscopicData gyroscopicData = GSON.fromJson(gyroscopicJson, GyroscopicData.class);
        final String gyroscopicDataJson = GSON.toJson(gyroscopicData);
        final AccelerometerData accelerometerData = GSON.fromJson(accelerometerJson, AccelerometerData.class);
        final String accelerometerDataJson = GSON.toJson(accelerometerData);


        System.out.println(gpsDataJson);
        System.out.println(gyroscopicDataJson);
        System.out.println(accelerometerDataJson);
        Assertions.assertEquals(
                JsonParser.parseString(gpsJson),
                JsonParser.parseString(gpsDataJson)
        );
        Assertions.assertEquals(
                JsonParser.parseString(gyroscopicJson),
                JsonParser.parseString(gyroscopicDataJson)
        );
        Assertions.assertEquals(
                JsonParser.parseString(accelerometerJson),
                JsonParser.parseString(accelerometerDataJson)
        );
    }

}