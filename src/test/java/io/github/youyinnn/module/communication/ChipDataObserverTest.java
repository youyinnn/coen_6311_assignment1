package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.GyroscopicData;
import io.github.youyinnn.bo.chip.RawDataMocker;
import io.github.youyinnn.bo.chip.UniversalChipData;
import io.github.youyinnn.module.chip.ChipDataProcessor;
import io.github.youyinnn.module.chip.ChipDataProcessorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ChipDataObserverTest {

    static ChipDataObserverImpl oa;
    static ChipDataObserverImpl ob;
    static ChipDataObserverImpl oc;

    static ChipDataSubjectImpl cs;

    static final RiskDetectorTestImpl riskDetectorTest = new RiskDetectorTestImpl();

    private static final ChipDataProcessor chipDataProcessor = new ChipDataProcessorImpl();
    private static final String CHIP_NO = "chip a";

    @BeforeAll
    static void beforeAll() {
        cs = new ChipDataSubjectImpl(riskDetectorTest);

        final MessageSenderTestImpl messageSenderTest = new MessageSenderTestImpl();

        oa = new ChipDataObserverImpl(CHIP_NO, "device a", messageSenderTest);
        ob = new ChipDataObserverImpl(CHIP_NO, "device b", messageSenderTest);
        oc = new ChipDataObserverImpl(CHIP_NO, "device c", messageSenderTest);

        // register all observers
        cs.register(oa, ob, oc);
    }

    @Test
    public void testObserverAndSubject() {
        final String[] universalDataSet = RawDataMocker.mockUniversalDataSet(1);

        final String[] gpsRawDataSplit = universalDataSet[0].split(System.lineSeparator());
        final String[] accelerometerRawDataSplit = universalDataSet[1].split(System.lineSeparator());
        final String[] gyroscopicRawDataSplit = universalDataSet[2].split(System.lineSeparator());

        ArrayList<UniversalChipData> universalChipDataArrayList = new ArrayList<>();

        for (int i = 0; i < gpsRawDataSplit.length; i++) {
            universalChipDataArrayList.add(new UniversalChipData(
                    chipDataProcessor.rawDataToGpsDataObject(CHIP_NO, gpsRawDataSplit[i]),
                    chipDataProcessor.rawDataToGyroscopicObject(CHIP_NO, gyroscopicRawDataSplit[i]),
                    chipDataProcessor.rawDataToAccelerometerObject(CHIP_NO, accelerometerRawDataSplit[i])
            ));
        }

        for (UniversalChipData universalChipData : universalChipDataArrayList) {
            cs.notifyObserver(universalChipData);
        }
    }

    @Test
    public void testObserverAndSubjectAtRisk() {
        final String[] universalDataSet = RawDataMocker.mockUniversalDataSet(1);

        final String[] gpsRawDataSplit = universalDataSet[0].split(System.lineSeparator());
        final String[] accelerometerRawDataSplit = universalDataSet[1].split(System.lineSeparator());
        final String[] gyroscopicRawDataSplit = universalDataSet[2].split(System.lineSeparator());

        ArrayList<UniversalChipData> universalChipDataArrayList = new ArrayList<>();

        for (int i = 0; i < gpsRawDataSplit.length; i++) {
            final GyroscopicData gyroscopicData = chipDataProcessor.rawDataToGyroscopicObject(CHIP_NO, gyroscopicRawDataSplit[i]);
            universalChipDataArrayList.add(new UniversalChipData(
                    chipDataProcessor.rawDataToGpsDataObject(CHIP_NO, gpsRawDataSplit[i]),
                    gyroscopicData,
                    chipDataProcessor.rawDataToAccelerometerObject(CHIP_NO, accelerometerRawDataSplit[i])
            ));
            riskDetectorTest.assertAtRisk(gyroscopicData);
        }

        for (UniversalChipData universalChipData : universalChipDataArrayList) {
            cs.notifyObserver(universalChipData);
        }
    }

}