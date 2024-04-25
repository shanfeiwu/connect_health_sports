package com.hnlens.ai.activities.adapters;

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
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.recyclerview_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(v->{
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view,viewHolder.getAbsoluteAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportMainActivivityAdapterData sportMainActivivityAdapterData = mData.get(position);
        holder.iconView.setImageResource(sportMainActivivityAdapterData.getIconId());
        holder.titleView.setText(sportMainActivivityAdapterData.getTitle());
        holder.summaryView.setText(sportMainActivivityAdapterData.getSummary());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iconView;
        public TextView titleView;
        public TextView summaryView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.recyclerview_item_icon);
            titleView = itemView.findViewById(R.id.recyclerview_item_title);
            summaryView = itemView.findViewById(R.id.recyclerview_item_summary);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int itemId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
