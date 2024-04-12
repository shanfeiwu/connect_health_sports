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

import java.time.ZonedDateTime;

/**
 * Represents an exercise session.
 */
public class ExerciseSession {
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String id;
    private String title;
    private HealthConnectAppInfo HealthConnectAppInfo;
    private MetadataInfo metadata;
    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.hnlens.ai.service.data.HealthConnectAppInfo getHealthConnectAppInfo() {
        return HealthConnectAppInfo;
    }

    public void setHealthConnectAppInfo(com.hnlens.ai.service.data.HealthConnectAppInfo healthConnectAppInfo) {
        HealthConnectAppInfo = healthConnectAppInfo;
    }

    public MetadataInfo getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataInfo metadata) {
        this.metadata = metadata;
    }
}