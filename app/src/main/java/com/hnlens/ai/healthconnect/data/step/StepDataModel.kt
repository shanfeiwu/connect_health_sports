package com.hnlens.ai.healthconnect.data.step

import android.content.Context
import androidx.health.connect.client.records.StepsRecord
import com.hnlens.ai.healthconnect.data.BaseData
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import com.hnlens.ai.utils.Constants
import java.time.Instant

class StepDataModel (val healthConnectManager: HealthConnectManager){

    suspend fun getTotalStepCount(startInstant: Instant,end:Instant):Long{
        var stepRecords = healthConnectManager.readStepSessions(startInstant,end)
        var totalStep = 0L
        for(stepRecord in stepRecords){
            totalStep += stepRecord.count
        }
        return totalStep
    }

    suspend fun collectStepData(startInstant: Instant,endInstant: Instant):MutableList<BaseData<Long>>{
        var stepRecords = healthConnectManager.readStepSessions(startInstant,endInstant)
        var stepsDatas = arrayListOf<BaseData<Long>>()
        var step:BaseData<Long>
        for(stepRecord in stepRecords){
            step = BaseData(stepRecord.metadata.id,stepRecord.count,null,stepRecord.startTime,stepRecord.endTime)
            stepsDatas.add(step)
        }
        return stepsDatas
    }

    fun getTargetStep(context: Context):Double{
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        val targetWeight = sp.getFloat(Constants.SP_TARGET_STEP,60f)
        return targetWeight.toDouble()
    }

    fun setTargetStep(context: Context, weightTarget:Double){
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putFloat(Constants.SP_TARGET_STEP, weightTarget.toFloat()).commit()
    }

}