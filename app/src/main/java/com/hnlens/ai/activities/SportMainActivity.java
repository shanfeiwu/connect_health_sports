package com.hnlens.ai.activities;

import static com.hnlens.ai.utils.Constants.SPORT_ITEM_ID_HEARTRATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnlens.ai.activities.adapters.SportMainActivivityAdapter;
import com.hnlens.ai.sports.R;
import com.hnlens.ai.utils.Constants;
import com.hnlens.ai.utils.DataUtils;

public class SportMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_main_layout);
        initView();
    }

    private void initView() {
        RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.sports_main_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(SportMainActivity.this,1, LinearLayoutManager.VERTICAL,false));
        SportMainActivivityAdapter sportMainActivivityAdapter = new SportMainActivivityAdapter(DataUtils.getSportMainActivivityAdapterDataList(getApplicationContext()));
        sportMainActivivityAdapter.setOnItemClickListener(this::onItemClick);
        mRecyclerView.setAdapter(sportMainActivivityAdapter);
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
}
