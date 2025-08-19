package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.entity.FriendsInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class rightListAdapter extends RecyclerView.Adapter<rightListAdapter.MyHolder>  {
    private List<FriendsInfo> mFriendsInfo = new ArrayList<>();
    private Random random = new Random();

    public void setListData(List<FriendsInfo> list) {
        // 创建列表副本以避免修改原始数据
        this.mFriendsInfo = new ArrayList<>(list);
        // 随机打乱列表顺序
        Collections.shuffle(this.mFriendsInfo, random);
        notifyDataSetChanged();
    }

    // 添加一个新方法来随机打乱现有数据
    public void shuffleData() {
        if (!mFriendsInfo.isEmpty()) {
            Collections.shuffle(mFriendsInfo, random);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        FriendsInfo friendsInfo = mFriendsInfo.get(position);
        holder.friends_name.setText(friendsInfo.getFriendsName());
        holder.friends_details.setText(friendsInfo.getFriendsDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (monItemClickListener != null) {
                    int currentPos = holder.getAdapterPosition();
                    if (currentPos != RecyclerView.NO_POSITION) {
                        monItemClickListener.onItemClick(friendsInfo, currentPos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFriendsInfo.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView friends_name;
        TextView friends_details;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            friends_name = itemView.findViewById(R.id.friends_name);
            friends_details = itemView.findViewById(R.id.friends_details);
        }
    }

    private onItemClickListener monItemClickListener;

    public void setMonItemClickListener(onItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(FriendsInfo friendsInfo, int position);
    }
}