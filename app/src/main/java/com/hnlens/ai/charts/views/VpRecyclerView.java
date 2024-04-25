package com.hnlens.ai.charts.views;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.hnlens.ai.activities.adapters.SideAlignSnapHelper;

public class VpRecyclerView extends RecyclerView {
    private static final String TAG = "VpRecyclerView";

    public VpRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public VpRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VpRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int position = 0;

    private void init() {
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, true);
        setLayoutManager(llm);
        SnapHelper snapHelper = new SideAlignSnapHelper(); //一次可滑动多个
        snapHelper.attachToRecyclerView(this);//居中显示RecyclerView
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int firs = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    if (position != firs) {
                        position = firs;
                        if (onpagerChageListener != null)
                            onpagerChageListener.onPagerChage(position);
                    }
                }
            }
        });
    }


    public void setOnPagerPosition(int position) {
//        this.position = position;
        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
        layoutManager.scrollToPosition(position);
    }

    public int getOnPagerPosition() {
        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }


    interface onPagerChageListener {
        void onPagerChage(int position);
    }

    private onPagerChageListener onpagerChageListener;

    public void setOnPagerChageListener(onPagerChageListener onpagerChageListener) {
        this.onpagerChageListener = onpagerChageListener;
    }

    @Override
    public void smoothScrollToPosition(int position) {
        Log.i(TAG,"smoothScrollToPosition");
        super.smoothScrollToPosition(position);
    }


}
