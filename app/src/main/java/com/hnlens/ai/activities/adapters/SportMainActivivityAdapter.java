package com.hnlens.ai.activities.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hnlens.ai.sports.R;

import java.util.List;

public class SportMainActivivityAdapter extends RecyclerView.Adapter<SportMainActivivityAdapter.ViewHolder> {

    private List<SportMainActivivityAdapterData> mData;

    private OnItemClickListener mOnItemClickListener;

    public SportMainActivivityAdapter(List<SportMainActivivityAdapterData> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, viewHolder.getAbsoluteAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportMainActivivityAdapterData sportMainActivivityAdapterData = mData.get(position);
        holder.iv_iconView.setImageResource(sportMainActivivityAdapterData.getIconId());
        holder.tv_titleView.setText(sportMainActivivityAdapterData.getTitle());
        holder.tv_item_data.setText(sportMainActivivityAdapterData.getData() + "");
        holder.tv_item_date.setText(sportMainActivivityAdapterData.getDate());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_iconView;
        public TextView tv_titleView;
        public TextView tv_item_data;
        public TextView tv_item_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_iconView = itemView.findViewById(R.id.recyclerview_item_icon);
            tv_titleView = itemView.findViewById(R.id.recyclerview_item_title);
            tv_item_data = itemView.findViewById(R.id.recyclerview_item_data);
            tv_item_date = itemView.findViewById(R.id.recyclerview_item_date);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int itemId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public <T> void updateAdapterData(int itemID, T data) {
        Log.i("demotest", "updateAdapterData: " + data + " itemID: "+itemID);
        for (SportMainActivivityAdapterData adapterData : mData) {
            Log.i("demotest","adapterData: "+adapterData.getId());
            if (adapterData.getId() == itemID) {
                if (data instanceof Double) {
                    adapterData.setData(((Double) data).longValue());
                } else {
                    adapterData.setData((Long) data);
                }
            }
        }
    }
}
