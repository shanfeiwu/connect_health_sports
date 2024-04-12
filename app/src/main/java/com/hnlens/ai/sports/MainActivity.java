package com.hnlens.ai.sports;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.hnlens.sdk.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.hnlens.ai.service.ISportInterface;
import com.hnlens.ai.service.data.ExerciseSession;
import com.hnlens.ai.service.data.ExerciseSessionData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements ServiceConnection, AdapterView.OnItemClickListener{
    private String TAG = "AIDL-translate";
    private Intent serviceIntent;

    //service aidl
    private ISportInterface mBinder;
    private ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent();
        serviceIntent.setAction("com.hnlens.ai.service.action.SportMAIN");
        serviceIntent.setPackage("com.hnlens.ai.service");
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
        list_view = findViewById(R.id.list_view);
        list_view.setOnItemClickListener(this);
    }

    public void translateReConnect() {
        unbindService(this);
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.v(TAG, "onServiceConnected");
        if (mBinder == null) {
            mBinder = ISportInterface.Stub.asInterface(iBinder);
            initList();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.v(TAG, "onServiceDisconnected");
        mBinder = null;
    }

    // 在子线程使用自定义Handler
    MyHandler myHandler = new MyHandler(Looper.getMainLooper());
    private List<ExerciseSession> array = new ArrayList<>();
    public void initList() {
        array.clear();
        if (mBinder != null) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(100);
                        String exerciseSessions = mBinder.readExerciseSessions(ISportInterface.HealthType.STEP);

                        if (exerciseSessions != null) {
                            Log.i(TAG,exerciseSessions);
                            JSONArray jsonArray = JSON.parseArray(exerciseSessions);
                            if(jsonArray!=null && !jsonArray.isEmpty()){
                                jsonArray.forEach(v->{
                                    array.add(JSON.parseObject(v.toString(),ExerciseSession.class));
                                });
                            }
                        }
                        myHandler.sendEmptyMessage(0);
                    } catch (RemoteException e) {
                        translateReConnect();
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        } else {
            translateReConnect();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ViewExerciseActivity.class);
        intent.putExtra("uuid", array.get(position).getMetadata().getId());
        startActivity(intent);
    }

    public class MyHandler extends Handler {
        // 在主线程中的Looper初始化Handler
        public MyHandler(Looper looper) {
            super(looper);
        }

        private static final String EXERCISE_NAME = "exercise_name";
        private static final String START_TIME = "start_time";
        private static final String END_TIME = "end_time";

        @Override
        public void handleMessage(Message msg) {
            if(array==null || array.isEmpty()){
                return;
            }
            // 处理消息，在这里更新UI
            List<Map<String, Object>> datas = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put(EXERCISE_NAME, array.get(i).getTitle());
                map.put(START_TIME, array.get(i).getStartTime());
                map.put(END_TIME, array.get(i).getEndTime());
                datas.add(map);
            }
            String[] from = {EXERCISE_NAME, START_TIME, END_TIME};
            int[] to = {R.id.name_txt, R.id.start_time, R.id.end_time};
            SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, datas, R.layout.exercise_list_item, from, to);
            list_view.setAdapter(simpleAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBinder != null) {
            initList();
        }
    }
}