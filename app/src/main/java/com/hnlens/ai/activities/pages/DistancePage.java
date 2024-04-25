package com.hnlens.ai.activities.pages;

import com.hnlens.ai.charts.views.ColumnBean;
import com.hnlens.ai.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DistancePage extends DetailBasePage{

    @Override
    void updateSummaryDataByTime(long startTime, long endTime, int startPostion, int endPostion) {
        mCurrentVisibleData = getCurrentTotalData(startPostion, endPostion) + " 公里";
    }

    @Override
    void initDetailData() {
        List<ColumnBean> columnBeans = new ArrayList<>();
        Random random = new Random();
        int maxValue = 1;
        for (int i = 0; i < Constants.DAY_BAR_COUNT; i++) {
            ColumnBean columnBean = new ColumnBean(Math.abs(random.nextInt(2000)), i + "", maxValue,i,Constants.FLAG_DETAIL_BY_DAY);
            if (maxValue < columnBean.getValue()) {
                maxValue = columnBean.getValue();
            }
            columnBeans.add(columnBean);
        }
        for (int i = 0; i < Constants.DAY_BAR_COUNT; i++) {
            columnBeans.get(i).setPercent((float) (columnBeans.get(i).getValue() * 1.0 / maxValue));
        }
        mDayColumnBeans = columnBeans;

        columnBeans = new ArrayList<>();
        maxValue = 1;
        for (int i = 0; i < Constants.WEEK_BAR_COUNT; i++) {
            ColumnBean columnBean = new ColumnBean(Math.abs(random.nextInt(2000)), "" + i, maxValue,i,Constants.FLAG_DETAIL_BY_WEEKLY);
            if (maxValue < columnBean.getValue()) {
                maxValue = columnBean.getValue();
            }
            columnBeans.add(columnBean);
        }
        for (int i = 0; i < Constants.WEEK_BAR_COUNT; i++) {
            columnBeans.get(i).setPercent((float) (columnBeans.get(i).getValue() * 1.0 / maxValue));
        }
        mWeeklyColumnBeans = columnBeans;

        columnBeans = new ArrayList<>();
        maxValue = 1;
        for (int i = 0; i < Constants.MONTH_BAR_COUNT; i++) {
            ColumnBean columnBean = new ColumnBean(Math.abs(random.nextInt(2000)), i + "", maxValue,i,Constants.FLAG_DETAIL_BY_MONTH);
            if (maxValue < columnBean.getValue()) {
                maxValue = columnBean.getValue();
            }
            columnBeans.add(columnBean);
        }
        for (int i = 0; i < Constants.MONTH_BAR_COUNT; i++) {
            columnBeans.get(i).setPercent((float) (columnBeans.get(i).getValue() * 1.0 / maxValue));
        }
        mMonthColumnBeans = columnBeans;
    }

}
