package com.hnlens.ai.healthconnect.data.demo

import android.util.Log
import androidx.health.connect.client.records.StepsRecord
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

class StepDataModel (val healthConnectManager: HealthConnectManager,var updateUIInterface: DataBaseModel.UpdateUIInterface){

    fun readTodayStepData(){
        CoroutineScope(Dispatchers.IO).launch {
            val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
            val now = Instant.now()
            var steps: List<StepsRecord> = readStepsRecordsByTime(startOfDay,now)
            var totalStep = 0L
            for (stepsRecord in steps) {
                totalStep += stepsRecord.count
            }
            if(updateUIInterface != null){
                withContext(Dispatchers.Main){
                    updateUIInterface.updateMainItemUI(totalStep,Constants.SPORT_ITEM_ID_STEP);
                }
            }
        }
    }

    fun collectAllDayDataDetail(start:Instant,end:Instant,offsetSeconeds:Long){
        CoroutineScope(Dispatchers.IO).launch {
            var timeStartTemp = start.plusSeconds(offsetSeconeds)
            var steps: MutableList<StepsRecord> = readStepsRecordsByTime(start,timeStartTemp).toMutableList()
            while (timeStartTemp.isBefore(end)){
                steps.addAll(readStepsRecordsByTime(timeStartTemp,timeStartTemp.plusSeconds(offsetSeconeds)))
                timeStartTemp = timeStartTemp.plusSeconds(offsetSeconeds)
            }
            if(updateUIInterface != null){
                withContext(Dispatchers.Main){
                    //updateUIInterface.updateUI(steps);
                }
            }
        }
    }

    private suspend fun readStepsRecordsByTime(startOfDay: Instant, now:Instant): MutableList<StepsRecord> {
        var steps: MutableList<StepsRecord> = healthConnectManager.readStepSessions(startOfDay, now);
        for (stepsRecord in steps) {
            Log.i("step", "stepsRecord-count: ${stepsRecord.count} " +
                    "stepsRecord-startTime: ${stepsRecord.startTime.atZone(ZoneId.systemDefault())}" +
                    " stepsRecord-endTime: ${stepsRecord.endTime.atZone(ZoneId.systemDefault())}" +
                    "stepsRecord-startZoneOffset: ${stepsRecord.startZoneOffset}" +
                    "stepsRecord-endZoneOffset: ${stepsRecord.endZoneOffset}" +
                    "stepsRecord-package-name: ${stepsRecord.metadata.dataOrigin.packageName}")
        }
        return steps
    }

}