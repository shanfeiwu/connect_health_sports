package com.hnlens.ai.utils;

import android.content.Context;

public class DisplayUtils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getDetailBarWidth(int currentCycleFlag, int containerWidth) {
        switch (currentCycleFlag) {
            case Constants.FLAG_DETAIL_BY_DAY -> {
                return containerWidth / Constants.DAY_BAR_COUNT;
            }
            case Constants.FLAG_DETAIL_BY_WEEKLY -> {
                return containerWidth / Constants.WEEK_BAR_COUNT;
            }
            case Constants.FLAG_DETAIL_BY_MONTH -> {
                return containerWidth / Constants.MONTH_BAR_COUNT;
            }
        }
        return containerWidth / Constants.DAY_BAR_COUNT;
    }

    public static boolean shouldDrawIndex(int itemIndex, int currentCycleFlag) {
        boolean shouldDraw = false;
        switch (currentCycleFlag) {
            case Constants.FLAG_DETAIL_BY_DAY -> {
                if (itemIndex % 6 == 0) {
                    shouldDraw = true;
                }
            }
            case Constants.FLAG_DETAIL_BY_WEEKLY -> {
                shouldDraw = true;
            }
            case Constants.FLAG_DETAIL_BY_MONTH -> {
                if (itemIndex % 7 == 0) {
                    shouldDraw = true;
                }
            }
        }
        return shouldDraw;
    }
}
