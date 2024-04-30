package com.hnlens.ai.healthconnect.data.demo

import android.util.Log
import androidx.health.connect.client.records.ExerciseSessionRecord
import com.hnlens.ai.healthconnect.data.ExerciseSessionData
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class ExceciseDataModel(val healthConnectManager: HealthConnectManager) {
    fun collectAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
            val now = Instant.now()
            var totalDistance = 0.0
            var totalStep = 0L
            var totalCalories = 0.0
            var exerciseSessionRecords: List<ExerciseSessionRecord> =
                healthConnectManager.readExerciseSessions(startOfDay, now)
            for (exerciseSessionRecord in exerciseSessionRecords) {
                var exerciseSessionData: ExerciseSessionData =
                    healthConnectManager.readAssociatedSessionData(exerciseSessionRecord.metadata.id)
                totalDistance += exerciseSessionData.totalDistance?.inMiles ?: 0.0
                totalStep += exerciseSessionData.totalSteps ?: 0L
                totalCalories += exerciseSessionData.totalEnergyBurned?.inKilojoules ?: 0.0
                Log.i("demotest","exerciseSessionRecord: packageName: ${exerciseSessionRecord.metadata.dataOrigin.packageName}" +
                        "startDate: ${exerciseSessionRecord.startTime.atZone(ZoneId.systemDefault())}" +
                        "totalStep: ${exerciseSessionData.totalSteps}")
            }
            Log.i("demotest","totalDistance: $totalDistance totalStep: $totalStep totalCalories: $totalCalories")
        }
    }




}