package com.hnlens.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnlens.ai.activities.adapters.SpaceItemDecoration;
import com.hnlens.ai.activities.adapters.SportMainActivivityAdapter;
import com.hnlens.ai.healthconnect.data.HealthConnectManager;
import com.hnlens.ai.healthconnect.data.demo.CaloriesDataModel;
import com.hnlens.ai.healthconnect.data.demo.DataBaseModel;
import com.hnlens.ai.healthconnect.data.demo.DistanceDataModel;
import com.hnlens.ai.healthconnect.data.demo.ExceciseDataModel;
import com.hnlens.ai.healthconnect.data.demo.StepDataModel;
import com.hnlens.ai.sports.R;
import com.hnlens.ai.utils.Constants;
import com.hnlens.ai.utils.DataUtils;

import java.time.Instant;

public class SportMainActivity extends AppCompatActivity implements DataBaseModel.UpdateUIInterface {

    private SportMainActivivityAdapter mSportMainActivivityAdapter;
    private HealthConnectManager mHealthConnectManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_main_layout);
        initView();
        mHealthConnectManager = new HealthConnectManager(getApplicationContext());
    }

    private void initView() {
        RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.sports_main_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(SportMainActivity.this,1, LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.space_item_height)));
        mSportMainActivivityAdapter = new SportMainActivivityAdapter(DataUtils.getSportMainActivivityAdapterDataList(getApplicationContext(),
                        0,
                        DataUtils.getDateShowing(Instant.now())));
        mSportMainActivivityAdapter.setOnItemClickListener(this::onItemClick);
        mRecyclerView.setAdapter(mSportMainActivivityAdapter);
    }

    private void onItemClick(View view, int itemID) {
        Toast.makeText(getApplicationContext(),"click item : "+itemID,Toast.LENGTH_SHORT).show();
        String title = DataUtils.getItemTitleById(itemID,getApplicationContext());
        Intent intent = new Intent("com.hnlens.ai.detail");
        intent.putExtra(Constants.INTENT_BUNDLE_ITEM_TITLE,title);
        intent.putExtra(Constants.INTENT_BUNDLE_ITEM_ID,itemID);
        intent.setClass(getApplicationContext(),DataDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        StepDataModel stepDataModel = new StepDataModel(mHealthConnectManager,this);
        CaloriesDataModel caloriesDataModel = new CaloriesDataModel(mHealthConnectManager,this);
        DistanceDataModel distanceDataModel = new DistanceDataModel(mHealthConnectManager,this);
        stepDataModel.readTodayStepData();
        caloriesDataModel.readTodayCaloriesData();
        distanceDataModel.readTodayDistanceData();

        ExceciseDataModel exceciseDataModel = new ExceciseDataModel(mHealthConnectManager);
        exceciseDataModel.collectAllData();
    }


    @Override
    public <T> void updateMainItemUI(T value, int type) {
        Log.i("demotest","value: "+value);
        mSportMainActivivityAdapter.updateAdapterData(type,value);
        mSportMainActivivityAdapter.notifyDataSetChanged();
    }
}
