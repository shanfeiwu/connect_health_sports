package com.hnlens.ai.healthconnect.data.exercise

import androidx.health.connect.client.records.ExerciseSessionRecord
import com.hnlens.ai.healthconnect.data.ExerciseSessionData
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import java.time.Instant

class ExerciseDataModel (val healthConnectManager: HealthConnectManager){

    suspend fun collectExerciseDate(start: Instant, end: Instant):List<ExerciseSessionRecord>{
        var exerciseSessionRecords: List<ExerciseSessionRecord> =
            healthConnectManager.readExerciseSessions(start, end)
        var exerciseSessionDataList = arrayListOf<ExerciseSessionData>()
        for (exerciseSessionRecord in exerciseSessionRecords){
            var exerciseSessionData: ExerciseSessionData =
                healthConnectManager.readAssociatedSessionData(exerciseSessionRecord.metadata.id)
            exerciseSessionDataList.add(exerciseSessionData)
        }
        return exerciseSessionRecords
    }


}
