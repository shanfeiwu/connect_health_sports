package com.hnlens.ai.healthconnect.data.distance

import android.content.Context
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import com.hnlens.ai.utils.Constants

class DistanceDataModel (val healthConnectManager: HealthConnectManager){

    fun getTargetDistance(context: Context):Double{
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        val targetWeight = sp.getFloat(Constants.SP_TARGET_DISTANCE,60f)
        return targetWeight.toDouble()
    }

    fun setTargetDistance(context: Context, weightTarget:Double){
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putFloat(Constants.SP_TARGET_DISTANCE, weightTarget.toFloat()).commit()
    }


}