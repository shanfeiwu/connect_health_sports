package com.hnlens.ai.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hnlens.ai.charts.views.BarItemView;
import com.hnlens.ai.charts.views.ColumnBean;
import com.hnlens.ai.sports.R;

import java.util.List;

public class BarChartBaseAdapter extends RecyclerView.Adapter<BarChartBaseAdapter.ViewHolder> {

    private List<ColumnBean> mColumnBeans;
    private Context mContext;

    public BarChartBaseAdapter(Context context, List<ColumnBean> columnBeans) {
        this.mColumnBeans = columnBeans;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BarItemView barItemView = (BarItemView) LayoutInflater.from(parent.getContext().getApplicationContext()).
                inflate(R.layout.bar_chart_recyclerview_layout, parent, false);
        return new ViewHolder(barItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.barItemView.setValue(mColumnBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mColumnBeans.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public BarItemView barItemView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            barItemView = (BarItemView) itemView;
        }
    }

    public void setColumnBeans(List<ColumnBean> columnBeans) {
        this.mColumnBeans = columnBeans;
    }
}
