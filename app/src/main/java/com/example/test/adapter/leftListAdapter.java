package com.example.test.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class leftListAdapter extends RecyclerView.Adapter<leftListAdapter.MyHoder> {

    private List<String> dataList=new ArrayList<>();

    private int current=0;
    public leftListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_list_item, null);
        return new MyHoder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull MyHoder holder, int position) {
        String name = dataList.get(position);
        holder.tv_name.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLeftListOnClickItemListner != null) {
                    mLeftListOnClickItemListner.onItemClick(position);
                }
            }
        });

        if(current==position){
            holder.itemView.setBackgroundResource(R.drawable.type_select);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.type_normol);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHoder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public MyHoder(@NonNull View itemView){
            super(itemView);
            tv_name=itemView.findViewById(R.id.name);
        }
    }



    private LeftListOnClickItemListner mLeftListOnClickItemListner;
    public void setmLeftListOnClickItemListner(LeftListOnClickItemListner mLeftListOnClickItemListner) {
        this.mLeftListOnClickItemListner = mLeftListOnClickItemListner;
    }

    public interface LeftListOnClickItemListner{
        void onItemClick(int position);
    }

    public void setCurrentIndex(int position) {
        this.current = position;
        notifyDataSetChanged();
    }
}