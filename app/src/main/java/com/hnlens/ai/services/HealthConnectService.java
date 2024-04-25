package com.hnlens.ai.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.hnlens.ai.service.ISportInterface;

public class HealthConnectService extends Service implements ServiceConnection {

    private ISportInterface mBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder.asBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.hnlens.ai.service.action.SportMAIN");
        serviceIntent.setPackage("com.hnlens.ai.service");
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (mBinder == null) {
            mBinder = ISportInterface.Stub.asInterface(iBinder);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        mBinder = null;
    }

    @Override
    public void onBindingDied(ComponentName name) {
        ServiceConnection.super.onBindingDied(name);
    }

    @Override
    public void onNullBinding(ComponentName name) {
        ServiceConnection.super.onNullBinding(name);
    }
}
