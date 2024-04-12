// ISportInterface.aidl
package com.hnlens.ai.service;

// Declare any non-default types here with import statements

interface ISportInterface {
        enum HealthType {
            STEP,WEIGHT
        }
    String getHealthData(HealthType healthType);

    String readExerciseSessions(HealthType healthType);
    void writeExerciseSession(HealthType healthType);
    void deleteExerciseSession(String uid);
    String readAssociatedSessionData(String uid);
}