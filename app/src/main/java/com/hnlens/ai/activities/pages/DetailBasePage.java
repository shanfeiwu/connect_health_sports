package com.hnlens.ai.activities.pages;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hnlens.ai.charts.views.ColumnBean;
import com.hnlens.ai.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class DetailBasePage {
    protected String mTitleTotalOrAverge;
    protected String mCurrentVisibleData;
    protected String mCurrentVisibleDate;
    protected List<ColumnBean> mDayColumnBeans = new ArrayList<>();
    protected List<ColumnBean> mWeeklyColumnBeans = new ArrayList<>();
    protected List<ColumnBean> mMonthColumnBeans = new ArrayList<>();

    protected int mCurrentCycleFlag = Constants.FLAG_DETAIL_BY_DAY;

    protected PageDataChangedListener mPageDataChangedListener;

    //根据起始时间和终点时间更新mSummary/mData/mDate
    abstract void updateSummaryDataByTime(long startTime, long endTime,int startPostion,int endPostion);

    abstract void initDetailData();

    public void initData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                initDetailData();
                if (mPageDataChangedListener != null) {
                    mPageDataChangedListener.onDataInitCompleted();
                }
            }
        });
    }

    public interface PageDataChangedListener {
        void onDataInitCompleted();

        void onPageDataChanged(String titleTotalOrAverge, String currentVisibleData, String currentVisibleDate);
    }

    public void updateData(Context context, long startTime, long endTime, int startPostion, int endPostion) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                updateSummaryDataByTime(startTime, endTime,startPostion,endPostion);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (mPageDataChangedListener != null) {
                            mPageDataChangedListener.onPageDataChanged(mTitleTotalOrAverge, mCurrentVisibleData, mCurrentVisibleDate);
                        }
                    }
                });
            }
        });
    }

    public void setPageDataChangedListener(PageDataChangedListener pageDataChangedListener) {
        this.mPageDataChangedListener = pageDataChangedListener;
    }

    public List<ColumnBean> getColumnBeans() {
        return getCurrentColumnBeans();
    }

    protected int getCurrentTotalData(int startPostion, int endPostion) {
        List<ColumnBean> list = getCurrentColumnBeans();
        if(list == null){
            return 0;
        }
        int totalData = 0;
        for (int i = startPostion; i <= endPostion; i++) {
            totalData += list.get(i).getValue();
        }
        return totalData;
    }

    private List<ColumnBean> getCurrentColumnBeans() {
        List<ColumnBean> list = null;
        switch (mCurrentCycleFlag){
            case Constants.FLAG_DETAIL_BY_DAY -> list = mDayColumnBeans;
            case Constants.FLAG_DETAIL_BY_WEEKLY -> list = mWeeklyColumnBeans;
            case Constants.FLAG_DETAIL_BY_MONTH -> list = mMonthColumnBeans;
        }
        return list;
    }

    protected int getCurrentAvgData(int startPostion, int endPostion) {
        return getCurrentTotalData(startPostion, endPostion) / (endPostion - startPostion + 1);
    }

    public void setmCurrentCycleFlag(int currentCycleFlag) {
        this.mCurrentCycleFlag = currentCycleFlag;
    }
}
