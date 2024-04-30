package com.hnlens.ai.healthconnect.data.demo

import android.util.Log
import androidx.health.connect.client.records.BasalMetabolicRateRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
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

class CaloriesDataModel (val healthConnectManager: HealthConnectManager, var updateUIInterface: DataBaseModel.UpdateUIInterface){

    fun readTodayCaloriesData(){
        CoroutineScope(Dispatchers.IO).launch {
            val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
            val now = Instant.now()
            var totalCaloriesBurnedRecords: List<TotalCaloriesBurnedRecord> = readTotalCaloriesRecordsByTime(startOfDay,now)
            var totalCaloriesBurnedRecordTemp = 0.0
            for (totalCaloriesBurnedRecord in totalCaloriesBurnedRecords) {
                if( "com.google.android.apps.fitness" == totalCaloriesBurnedRecord.metadata.dataOrigin.packageName
                ){
                    Log.i("testdemo","packageName ${totalCaloriesBurnedRecord.metadata.dataOrigin.packageName}")
                    totalCaloriesBurnedRecordTemp += totalCaloriesBurnedRecord.energy.inKilocalories
                }
            }
            //"com.hnlens.ai.service" == totalCaloriesBurnedRecord.metadata.dataOrigin.packageName
            //                    ||
            if(updateUIInterface != null){
                withContext(Dispatchers.Main){
                    updateUIInterface.updateMainItemUI(totalCaloriesBurnedRecordTemp, Constants.SPORT_ITEM_ID_CALORIES);
                }
            }
        }
    }

    fun readTodayBasalCaloriesData(){
        CoroutineScope(Dispatchers.IO).launch {
            val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
            val now = Instant.now()
            var totalCaloriesBurnedRecords: List<BasalMetabolicRateRecord> = readBasalCaloriesRecordsByTime(startOfDay,now)
            var totalCaloriesBurnedRecordTemp = 0.0
            for (totalCaloriesBurnedRecord in totalCaloriesBurnedRecords) {
                totalCaloriesBurnedRecordTemp += totalCaloriesBurnedRecord.basalMetabolicRate.inKilocaloriesPerDay
            }
            if(updateUIInterface != null){
                withContext(Dispatchers.Main){
                    updateUIInterface.updateMainItemUI(totalCaloriesBurnedRecordTemp, Constants.SPORT_ITEM_ID_CALORIES);
                }
            }
        }
    }

    private suspend fun readTotalCaloriesRecordsByTime(startOfDay: Instant, now:Instant): MutableList<TotalCaloriesBurnedRecord> {
        var totalCaloriesBurnedRecords: MutableList<TotalCaloriesBurnedRecord> = healthConnectManager.readTotalCaloriesSessions(startOfDay, now);
        for (totalCaloriesBurnedRecord in totalCaloriesBurnedRecords){
            Log.i("demotest",
                "startTime: ${totalCaloriesBurnedRecord.startTime.atZone(ZoneId.systemDefault())}"+
                " endTime: ${totalCaloriesBurnedRecord.endTime.atZone(ZoneId.systemDefault())}"+
                " energy: ${totalCaloriesBurnedRecord.energy}"+
                " inCalories: ${totalCaloriesBurnedRecord.energy.inCalories}"+
                " dataOrigin: ${totalCaloriesBurnedRecord.metadata.dataOrigin.packageName}"+
                " inKilocalories: ${totalCaloriesBurnedRecord.energy.inKilocalories}"+
                " inJoules: ${totalCaloriesBurnedRecord.energy.inJoules}"+
                " inKilojoules: ${totalCaloriesBurnedRecord.energy.inKilojoules}"
            )
        }
        return totalCaloriesBurnedRecords
    }

    private suspend fun readBasalCaloriesRecordsByTime(startOfDay: Instant, now:Instant): MutableList<BasalMetabolicRateRecord> {
        var totalCaloriesBurnedRecords: MutableList<BasalMetabolicRateRecord> = healthConnectManager.readBasalCaloriesSessions(startOfDay, now);
        for (totalCaloriesBurnedRecord in totalCaloriesBurnedRecords){

            Log.i("demotest",
                "inKilocaloriesPerDay: ${totalCaloriesBurnedRecord.basalMetabolicRate.inKilocaloriesPerDay}"+
                " endTime: ${totalCaloriesBurnedRecord.time}"
            )
        }
        return totalCaloriesBurnedRecords
    }
}