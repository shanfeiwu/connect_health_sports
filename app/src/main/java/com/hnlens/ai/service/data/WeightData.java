/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *private  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hnlens.ai.service.data;

import androidx.health.connect.client.units.Mass;

import java.time.ZonedDateTime;

/**
 * Represents a weight record and associated data.
 */
public class WeightData {
    private Mass weight;
    private String id;
    private ZonedDateTime time;
    private HealthConnectAppInfo sourceAppInfo;

    public Mass getWeight() {
        return weight;
    }

    public void setWeight(Mass weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public HealthConnectAppInfo getSourceAppInfo() {
        return sourceAppInfo;
    }

    public void setSourceAppInfo(HealthConnectAppInfo sourceAppInfo) {
        this.sourceAppInfo = sourceAppInfo;
    }
}