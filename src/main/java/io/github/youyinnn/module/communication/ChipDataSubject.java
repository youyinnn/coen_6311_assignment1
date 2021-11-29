package io.github.youyinnn.module.communication;

import io.github.youyinnn.bo.chip.UniversalChipData;

/**
 * @author yinnnyou
 */
public interface ChipDataSubject {

    void register(ChipDataObserver ... o);
    void unregister(ChipDataObserver o);
    void notifyObserver(UniversalChipData universalChipData);

}
