package com.hnlens.ai.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hnlens.ai.sports.R;

public class PersonInfomatinActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_current_weight;
    private LinearLayout ll_target_weight;
    private LinearLayout ll_target_step;
    private LinearLayout ll_exercise_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infomation);

        ll_current_weight = findViewById(R.id.current_weight);
        ll_target_weight = findViewById(R.id.target_weight);
        ll_target_step = findViewById(R.id.target_step);
        ll_exercise_time = findViewById(R.id.target_exercise_time);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.current_weight){

        } else if (view.getId() == R.id.target_weight) {

        }else if (view.getId() == R.id.target_step) {

        }else if (view.getId() == R.id.target_exercise_time) {

        }
    }
}
