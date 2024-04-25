package com.hnlens.ai.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnlens.ai.activities.adapters.BarChartBaseAdapter;
import com.hnlens.ai.activities.pages.CaloriesPage;
import com.hnlens.ai.activities.pages.DetailBasePage;
import com.hnlens.ai.activities.pages.DistancePage;
import com.hnlens.ai.activities.pages.StepPage;
import com.hnlens.ai.charts.views.ColumnBean;
import com.hnlens.ai.charts.views.VpRecyclerView;
import com.hnlens.ai.sports.R;
import com.hnlens.ai.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataDetailActivity extends AppCompatActivity implements DetailBasePage.PageDataChangedListener, View.OnClickListener {
    private static final String TAG = "DataDetailActivity";

    private int mSportsItemID = -1;

    private VpRecyclerView mVpRecyclerView;
    private TextView mTitleView;
    private TextView mDateView;
    private TextView mDateTextView;
    private BarChartBaseAdapter mBarChartBaseAdapter;
    private DetailBasePage mDetailPage;
    private Button btnDay;
    private Button btnWeekly;
    private Button btnMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_data_detail_layout);
        initView(getIntent());
    }

    private void initView(Intent intent) {
        mSportsItemID = intent.getIntExtra(Constants.INTENT_BUNDLE_ITEM_ID, -1);
        mTitleView = findViewById(R.id.detail_title_view);
        mTitleView.setText(intent.getStringExtra(Constants.INTENT_BUNDLE_ITEM_TITLE));

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date(currentTimeMillis);
        String currentTime = sdf.format(date);
        mDateView = findViewById(R.id.detail_titel_date_view);
        mDateView.setText(currentTime);

        mDateTextView = findViewById(R.id.tv_detail_data);
        mDateTextView.setText(R.string.sport_main_item_summary_default);

        mDetailPage = getPageById(mSportsItemID);
        mDetailPage.setPageDataChangedListener(this);
        mDetailPage.initData();

        btnDay = findViewById(R.id.btn_date_day);
        btnWeekly = findViewById(R.id.btn_date_week);
        btnMonth = findViewById(R.id.btn_date_month);
        btnDay.setOnClickListener(this);
        btnWeekly.setOnClickListener(this);
        btnMonth.setOnClickListener(this);
    }

    private void initRecyclerView() {
        //生成测试数据
        List<ColumnBean> columnBeans = new ArrayList<>();
        Random random = new Random();
        int maxValue = 1;
        for (int i = 0; i < 120; i++) {
            ColumnBean columnBean = new ColumnBean(random.nextInt(2000), i + "月", maxValue, i, Constants.FLAG_DETAIL_BY_DAY);
            if (maxValue < columnBean.getValue()) {
                maxValue = columnBean.getValue();
            }
            columnBeans.add(columnBean);
        }
        for (int i = 0; i < 120; i++) {
            columnBeans.get(i).setPercent((float) (columnBeans.get(i).getValue() * 1.0 / maxValue));
        }

        mVpRecyclerView = findViewById(R.id.bar_chart_recycler_view);
//        mBarChartBaseAdapter = new BarChartBaseAdapter(getApplicationContext(),columnBeans);
        mBarChartBaseAdapter = new BarChartBaseAdapter(getApplicationContext(), mDetailPage.getColumnBeans());
        mVpRecyclerView.setAdapter(mBarChartBaseAdapter);
        mVpRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                updateCurrentVisibleData(recyclerView);
            }
        });
    }

    private void uptateUI(String titleTotalOrAverge, String currentVisibleData, String currentVisibleDate) {
        //根据返回数据，更新UI
        mDateTextView.setText(currentVisibleData);
    }

    private DetailBasePage getPageById(int itemId) {
        DetailBasePage detailBasePage = null;
        switch (itemId) {
            case Constants.SPORT_ITEM_ID_STEP -> {
                detailBasePage = new StepPage();
            }
            case Constants.SPORT_ITEM_ID_DISTANCE -> {
                detailBasePage = new DistancePage();
            }
            case Constants.SPORT_ITEM_ID_CALORIES -> {
                detailBasePage = new CaloriesPage();
            }
        }
        return detailBasePage;
    }


    @Override
    public void onDataInitCompleted() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
                //updateCurrentVisibleData(mVpRecyclerView);
            }
        });
    }

    @Override
    public void onPageDataChanged(String titleTotalOrAverge, String currentVisibleData, String currentVisibleDate) {
        uptateUI(titleTotalOrAverge, currentVisibleData, currentVisibleDate);
    }

    private void updateCurrentVisibleData(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        BarChartBaseAdapter.ViewHolder firstViewHolder = (BarChartBaseAdapter.ViewHolder) recyclerView.findViewHolderForLayoutPosition(layoutManager.findFirstVisibleItemPosition());
        BarChartBaseAdapter.ViewHolder lastViewHolder = (BarChartBaseAdapter.ViewHolder) recyclerView.findViewHolderForLayoutPosition(layoutManager.findLastVisibleItemPosition());
        long startTimeStamp = firstViewHolder.barItemView.getColumnBean().getTimeStamp();
        long endTimeStamp = lastViewHolder.barItemView.getColumnBean().getTimeStamp();
        mDetailPage.updateData(getApplicationContext(), startTimeStamp, endTimeStamp, layoutManager.findFirstVisibleItemPosition(), layoutManager.findLastVisibleItemPosition());
    }


    @Override
    public void onClick(View view) {
        int currentCycleFlag = Constants.FLAG_DETAIL_BY_DAY;
        if (view.getId() == R.id.btn_date_week) {
            currentCycleFlag = Constants.FLAG_DETAIL_BY_WEEKLY;
        } else if (view.getId() == R.id.btn_date_month) {
            currentCycleFlag = Constants.FLAG_DETAIL_BY_MONTH;
        }
        Log.i(TAG,"currentCycleFlag: "+currentCycleFlag);
        switchPeriod(currentCycleFlag);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void switchPeriod(int currentCycleFlag) {
        switch (currentCycleFlag) {
            case Constants.FLAG_DETAIL_BY_DAY -> {
                mDetailPage.setmCurrentCycleFlag(Constants.FLAG_DETAIL_BY_DAY);
                mBarChartBaseAdapter.setColumnBeans(mDetailPage.getColumnBeans());
            }
            case Constants.FLAG_DETAIL_BY_WEEKLY -> {
                mDetailPage.setmCurrentCycleFlag(Constants.FLAG_DETAIL_BY_WEEKLY);
                mBarChartBaseAdapter.setColumnBeans(mDetailPage.getColumnBeans());
            }
            case Constants.FLAG_DETAIL_BY_MONTH -> {
                mDetailPage.setmCurrentCycleFlag(Constants.FLAG_DETAIL_BY_MONTH);
                mBarChartBaseAdapter.setColumnBeans(mDetailPage.getColumnBeans());
            }
        }
        mBarChartBaseAdapter.notifyDataSetChanged();
    }
}
