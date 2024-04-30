package com.hnlens.ai.utils;

public class Constants {

    /**
     * 设在主页显示item数量，并定义每个item id
     */
    public static final int SPORT_ITEM_SIZE = 3;
    public static final int SPORT_ITEM_ID_STEP = 0;
    public static final int SPORT_ITEM_ID_CALORIES = 1;
    public static final int SPORT_ITEM_ID_DISTANCE = 2;
    public static final int SPORT_ITEM_ID_BLOODPRESSURE = 3;
    public static final int SPORT_ITEM_ID_SPROTRECORD = 4;
    public static final int SPORT_ITEM_ID_MODERATEEXERCISE = 5;
    public static final int SPORT_ITEM_ID_HEARTRATE = 6;

    /**
     * 定义Intent key
     */
    public static final String INTENT_BUNDLE_ITEM_ID = "item_id";
    public static final String INTENT_BUNDLE_ITEM_TITLE = "item_title";

    public static final int FLAG_DETAIL_BY_DAY = 0;
    public static final int FLAG_DETAIL_BY_WEEKLY = 1;
    public static final int FLAG_DETAIL_BY_MONTH = 2;

    public static final int DAY_BAR_COUNT = 24;
    public static final int WEEK_BAR_COUNT = 7;
    public static final int MONTH_BAR_COUNT = 31;

    public static final String SP_NAME = "save_data";
    public static final String SP_TARGET_WEIGHT = "target_weight";
    public static final String SP_TARGET_STEP = "target_step";
    public static final String SP_TARGET_DISTANCE = "target_distance";
    public static final String SP_TARGET_CALORIES = "target_calories";

}
