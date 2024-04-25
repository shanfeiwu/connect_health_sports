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
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hnlens.ai.service.ISportInterface;


public class ViewExerciseActivity extends AppCompatActivity implements ServiceConnection, View.OnClickListener {
    private String TAG = "AIDL-ViewExerciseActivity";
    private Intent serviceIntent;
    //service aidl
    private ISportInterface mBinder;
    private TextView info_txt;
    private Button delete;
    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exercise);
        info_txt = findViewById(R.id.info_txt);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        serviceIntent = new Intent();
        serviceIntent.setAction("com.hnlens.ai.service.action.SportMAIN");
        serviceIntent.setPackage("com.hnlens.ai.service");
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);

        uuid = getIntent().getStringExtra("uuid");
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
            initViews();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.v(TAG, "onServiceDisconnected");
        mBinder = null;
    }

    // 在子线程使用自定义Handler
    private final MyHandler myHandler = new MyHandler(Looper.getMainLooper());
    private String exerciseInfo;

    public void initViews() {
        if (mBinder != null) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Log.v(TAG, "initViews");
                        exerciseInfo = mBinder.readAssociatedSessionData(uuid);
                        Log.v(TAG, "exerciseInfo： "+exerciseInfo);
                        if (exerciseInfo != null) {
                            Log.i(TAG, exerciseInfo);
                        }
                        myHandler.sendEmptyMessage(0);
                    } catch (RemoteException e) {
                        translateReConnect();
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        } else {
            translateReConnect();
        }
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
            // 处理消息，在这里更新UI
            info_txt.setText(exerciseInfo);
        }
    }

    public static void Log(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }


    @Override
    public void onClick(View v) {
        try {
            mBinder.deleteExerciseSession(uuid);
            finish();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}