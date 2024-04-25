package com.hnlens.ai.activities.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SideAlignSnapHelper extends LinearSnapHelper {

    private OrientationHelper helper;
    private RecyclerView mRecyclerView;
    private boolean mReverse = false;

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = null;
        if (mRecyclerView != null) {
            layoutManager = mRecyclerView.getLayoutManager();
        }
        if (layoutManager instanceof LinearLayoutManager) {
            mReverse = ((LinearLayoutManager) layoutManager).getReverseLayout();
        }
    }

    private OrientationHelper getHelper(RecyclerView.LayoutManager layoutManager) {
        if (helper == null) {
            helper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return helper;
    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        return out;
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        int distance = 0;
        if (mReverse) {
            distance = helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
        } else {
            distance = helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
        }
        return distance;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartView(layoutManager, getHelper(layoutManager));
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        if (firstChild == RecyclerView.NO_POSITION) {
            return null;
        }

        View firstChildView = layoutManager.findViewByPosition(firstChild);
        View lastChildView = layoutManager.findViewByPosition(lastChild);
        int start = 0, end = 0;
        if (mReverse) {
            start = Math.abs(helper.getTotalSpace() - helper.getDecoratedStart(firstChildView));
            end = helper.getDecoratedEnd(lastChildView);
        } else {
            start = helper.getDecoratedEnd(firstChildView);
            end = Math.abs(helper.getDecoratedStart(lastChildView) - helper.getTotalSpace());
        }
        if (start >= end) {
            return firstChildView;
        } else {
            return layoutManager.findViewByPosition(firstChild + 1);
        }
    }
}
