package com.hnlens.ai.healthconnect.data.Calories

import android.content.Context
import com.hnlens.ai.healthconnect.data.HealthConnectManager
import com.hnlens.ai.utils.Constants

class CaloriesDataModel(val healthConnectManager: HealthConnectManager) {
    fun getTargetCalories(context: Context):Double{
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        val targetWeight = sp.getFloat(Constants.SP_TARGET_CALORIES,60f)
        return targetWeight.toDouble()
    }

    fun setTargetStep(context: Context, weightTarget:Double){
        val sp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putFloat(Constants.SP_TARGET_CALORIES, weightTarget.toFloat()).commit()
    }
}