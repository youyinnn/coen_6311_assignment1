package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.AccelerometerData;
import io.github.youyinnn.bo.chip.GpsData;
import io.github.youyinnn.bo.chip.GyroscopicData;
import io.github.youyinnn.bo.chip.UniversalChipData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChipDataSubjectImpl implements ChipDataSubject {

    private static final HashMap<String, List<ChipDataObserver>> CHIP_AND_DEVICE_MAP
            = new HashMap<>(16);

    private final RiskDetector riskDetector;

    public ChipDataSubjectImpl(RiskDetector riskDetector) {
        this.riskDetector = riskDetector;
    }

    @Override
    public void register(ChipDataObserver ... obs) {
        for (ChipDataObserver o : obs) {
            ChipDataObserverImpl observer = (ChipDataObserverImpl) o;
            final String chipNo = observer.getChipNo();
            CHIP_AND_DEVICE_MAP.putIfAbsent(chipNo, new ArrayList<>(16));
            CHIP_AND_DEVICE_MAP.get(chipNo).add(o);
        }
    }

    @Override
    public void unregister(ChipDataObserver o) {
        ChipDataObserverImpl observer = (ChipDataObserverImpl) o;
        final String chipNo = observer.getChipNo();
        CHIP_AND_DEVICE_MAP.get(chipNo).remove(o);
    }

    @Override
    public void notifyObserver(UniversalChipData universalChipData) {
        final GpsData gpsData = universalChipData.getGpsData();
        final String chipNo = gpsData.getChipNo();
        final GyroscopicData gyroscopicData = universalChipData.getGyroscopicData();
        final AccelerometerData accelerometerData = universalChipData.getAccelerometerData();
        for (ChipDataObserver observer : CHIP_AND_DEVICE_MAP.get(chipNo)) {
            observer.updateChipData(gpsData, gyroscopicData, accelerometerData);
            if (riskDetector.isAtRisk(gyroscopicData)) {
                observer.updateState(BikeState.AtRisk);
            }
        }
    }
}
