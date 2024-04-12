/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hnlens.ai.service.data;

import androidx.health.connect.client.records.HeartRateRecord;
import androidx.health.connect.client.records.SpeedRecord;
import androidx.health.connect.client.units.Energy;
import androidx.health.connect.client.units.Length;
import androidx.health.connect.client.units.Velocity;
import java.time.Duration;
import java.util.List;

/**
 * Represents data, both aggregated and raw, associated with a single exercise session. Used to
 * collate results from aggregate and raw reads from Health Connect in one object.
 */
public class ExerciseSessionData {
    String uid;
    Duration totalActiveTime;
    Long totalSteps;
    Length totalDistance;
    Energy totalEnergyBurned;
    Long minHeartRate;
    Long maxHeartRate;
    Long avgHeartRate;
    List<HeartRateRecord> heartRateSeries;

    Velocity minSpeed;
    Velocity maxSpeed;
    Velocity avgSpeed;
    List<SpeedRecord> speedRecord;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Duration getTotalActiveTime() {
        return totalActiveTime;
    }

    public void setTotalActiveTime(Duration totalActiveTime) {
        this.totalActiveTime = totalActiveTime;
    }

    public Long getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Long totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Length getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Length totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Energy getTotalEnergyBurned() {
        return totalEnergyBurned;
    }

    public void setTotalEnergyBurned(Energy totalEnergyBurned) {
        this.totalEnergyBurned = totalEnergyBurned;
    }

    public Long getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(Long minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public Long getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Long maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Long getAvgHeartRate() {
        return avgHeartRate;
    }

    public void setAvgHeartRate(Long avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }

    public List<HeartRateRecord> getHeartRateSeries() {
        return heartRateSeries;
    }

    public void setHeartRateSeries(List<HeartRateRecord> heartRateSeries) {
        this.heartRateSeries = heartRateSeries;
    }

    public Velocity getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Velocity minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Velocity getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Velocity maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Velocity getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Velocity avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public List<SpeedRecord> getSpeedRecord() {
        return speedRecord;
    }

    public void setSpeedRecord(List<SpeedRecord> speedRecord) {
        this.speedRecord = speedRecord;
    }
}
