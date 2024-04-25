package com.hnlens.ai.healthconnect.interfaces;

import java.time.Instant;

public interface SportsDataOption {
    int[] getHeartRates(Instant start,Instant end);
    int[] getBloodPressue(Instant start,Instant end);
    int[] getStep(Instant start,Instant end);
}
