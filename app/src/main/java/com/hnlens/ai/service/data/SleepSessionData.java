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

import androidx.health.connect.client.records.SleepSessionRecord;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Represents sleep data, raw, aggregated and sleep stages, for a given [SleepSessionRecord].
 */
public class SleepSessionData {
    private String uid;
    private String title;
    private String notes;
    private Instant startTime;
    private ZoneOffset startZoneOffset;
    private Instant endTime;
    private ZoneOffset endZoneOffset;
    private Duration duration;
    private List<SleepSessionRecord.Stage> stages;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public ZoneOffset getStartZoneOffset() {
        return startZoneOffset;
    }

    public void setStartZoneOffset(ZoneOffset startZoneOffset) {
        this.startZoneOffset = startZoneOffset;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public ZoneOffset getEndZoneOffset() {
        return endZoneOffset;
    }

    public void setEndZoneOffset(ZoneOffset endZoneOffset) {
        this.endZoneOffset = endZoneOffset;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<SleepSessionRecord.Stage> getStages() {
        return stages;
    }

    public void setStages(List<SleepSessionRecord.Stage> stages) {
        this.stages = stages;
    }
}
