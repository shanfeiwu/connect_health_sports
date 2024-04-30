package com.hnlens.ai.healthconnect.data.weight

import android.content.Context
import com.hnlens.ai.healthconnect.data.BaseData
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import com.hnlens.ai.utils.Constants
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class WeightDataModel (val healthConnectManager: HealthConnectManager){

    suspend fun getCurrentWeight():Double{
        val now = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant()
        val startInstant = Instant.MIN
        val weightRecords = healthConnectManager.readWeightInputs(startInstant,now)
        return weightRecords[0].weight.inKilograms
    }

    fun getTargetWeight(context: Context):Double{
        val sp = context.getSharedPreferences(Constants.SP_NAME,Context.MODE_PRIVATE)
        val targetWeight = sp.getFloat(Constants.SP_TARGET_WEIGHT,60f)
        return targetWeight.toDouble()
    }

    fun setTargetWeight(context: Context,weightTarget:Double){
        val sp = context.getSharedPreferences(Constants.SP_NAME,Context.MODE_PRIVATE)
        sp.edit().putFloat(Constants.SP_TARGET_WEIGHT, weightTarget.toFloat()).commit()
    }

    suspend fun submitCurrentWeight(weightInput: Double){
        healthConnectManager.writeWeightInput(weightInput)
    }

    suspend fun computeWeeklyAverage(start: Instant, end: Instant){
        healthConnectManager.computeWeeklyAverage(start,end)
    }

    suspend fun collectWeights(start: Instant, end: Instant):List<BaseData<Double>>{
        val weightRecords = healthConnectManager.readWeightInputs(start,end)
        var weightData:BaseData<Double>
        val weightDatas = arrayListOf<BaseData<Double>>()
        for(weightRecord in weightRecords){
            weightData = BaseData(weightRecord.metadata.id,weightRecord.weight.inKilograms,weightRecord.time,null,null)
            weightDatas.add(weightData)
        }
        return weightDatas
    }
}