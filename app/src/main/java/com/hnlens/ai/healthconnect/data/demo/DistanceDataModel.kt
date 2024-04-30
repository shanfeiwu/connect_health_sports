package com.hnlens.ai.healthconnect.data.demo

import android.util.Log
import androidx.health.connect.client.records.DistanceRecord
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import com.hnlens.ai.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class DistanceDataModel(
    val healthConnectManager: HealthConnectManager,
    var updateUIInterface: DataBaseModel.UpdateUIInterface
) {
    fun readTodayDistanceData(){
        CoroutineScope(Dispatchers.IO).launch {
            val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
            val now = Instant.now()
            var distanceRecords: List<DistanceRecord> = readStepsRecordsByTime(startOfDay,now)
            var totalDistanceRecords = 0.0
            for (distanceRecord in distanceRecords) {
                totalDistanceRecords += distanceRecord.distance.inMiles
            }
            Log.i("demotest","totalDistanceRecords $totalDistanceRecords")
            if(updateUIInterface != null){
                withContext(Dispatchers.Main){
                    updateUIInterface.updateMainItemUI(totalDistanceRecords, Constants.SPORT_ITEM_ID_DISTANCE);
                }
            }
        }
    }

    private suspend fun readStepsRecordsByTime(startOfDay: Instant, now: Instant): MutableList<DistanceRecord> {
        var distanceRecords: MutableList<DistanceRecord> = healthConnectManager.readDistanceSessions(startOfDay, now);
        Log.i("demotest","distanceRecords: ${distanceRecords.size}")
//        for (distanceRecord in distanceRecords){
//            Log.i("demotest",
//                "startTime: ${distanceRecord.startTime.atZone(ZoneId.systemDefault())}"+
//                        " endTime: ${distanceRecord.endTime.atZone(ZoneId.systemDefault())}"+
//                        " distance: ${distanceRecord.distance}" +
//                        " metadata: ${distanceRecord.metadata.toString()}"
//            )
//        }
        return distanceRecords
    }
}