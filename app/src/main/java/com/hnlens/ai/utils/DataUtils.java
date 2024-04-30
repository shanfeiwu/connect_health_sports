package com.hnlens.ai.utils;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateUtils;
import android.util.Log;

import com.hnlens.ai.activities.adapters.SportMainActivivityAdapterData;
import com.hnlens.ai.sports.R;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataUtils {
    private static final String TAG = "DataUtils";

    public static List<SportMainActivivityAdapterData> getSportMainActivivityAdapterDataList(Context context,long totalData,String date){
        List<SportMainActivivityAdapterData> sportMainActivivityAdapterDataList = new ArrayList<>();
        for(int i = 0;i<Constants.SPORT_ITEM_SIZE;i++){
            switch (i){
                case Constants.SPORT_ITEM_ID_HEARTRATE -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_HEARTRATE,
                        context.getResources().getString(R.string.sport_main_item_title_heart_rate)
                ));
                case Constants.SPORT_ITEM_ID_BLOODPRESSURE -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_BLOODPRESSURE,context.getResources().getString(R.string.sport_main_item_title_blood_pressure)
                ));
                case Constants.SPORT_ITEM_ID_STEP -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_STEP,context.getResources().getString(R.string.sport_main_item_title_step)
                ));
                case Constants.SPORT_ITEM_ID_CALORIES -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_CALORIES,context.getResources().getString(R.string.sport_main_item_title_calories)
                ));
                case Constants.SPORT_ITEM_ID_SPROTRECORD -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_SPROTRECORD,context.getResources().getString(R.string.sport_main_item_title_sport_record)
                ));
                case Constants.SPORT_ITEM_ID_MODERATEEXERCISE -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_MODERATEEXERCISE,context.getResources().getString(R.string.sport_main_item_title_moderate_exercise)
                ));
                case Constants.SPORT_ITEM_ID_DISTANCE -> sportMainActivivityAdapterDataList.add(new SportMainActivivityAdapterData(
                        totalData,
                        date,
                        R.drawable.ic_health_connect_logo,
                        Constants.SPORT_ITEM_ID_DISTANCE,context.getResources().getString(R.string.sport_main_item_title_distance)
                ));
            }
        }
        return sportMainActivivityAdapterDataList;
    }

    public static String getItemTitleById(int itemId,Context context){
        String title = null;
        switch (itemId){
            case Constants.SPORT_ITEM_ID_HEARTRATE ->  title = context.getResources().getString(R.string.sport_main_item_title_heart_rate);
            case Constants.SPORT_ITEM_ID_BLOODPRESSURE -> title = context.getResources().getString(R.string.sport_main_item_title_blood_pressure);
            case Constants.SPORT_ITEM_ID_STEP -> title = context.getResources().getString(R.string.sport_main_item_title_step);
            case Constants.SPORT_ITEM_ID_CALORIES -> title = context.getResources().getString(R.string.sport_main_item_title_calories);
            case Constants.SPORT_ITEM_ID_SPROTRECORD -> title = context.getResources().getString(R.string.sport_main_item_title_sport_record);
            case Constants.SPORT_ITEM_ID_MODERATEEXERCISE -> title = context.getResources().getString(R.string.sport_main_item_title_moderate_exercise);
            case Constants.SPORT_ITEM_ID_DISTANCE -> title = context.getResources().getString(R.string.sport_main_item_title_distance);
        }
        return title;
    }

    public static String getDateShowing(Instant instant){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        Log.i(TAG,format.format(instant.toEpochMilli())+"");
        return  format.format(new Date(instant.toEpochMilli()));
    }
}
