package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ac.MainActivity;
import com.example.test.ac.addFriends;
import com.example.test.ac.friendsDetails;
import com.example.test.adapter.leftListAdapter;
import com.example.test.adapter.rightListAdapter;
import com.example.test.entity.DataService;
import com.example.test.entity.FriendsInfo;

import java.util.ArrayList;
import java.util.List;

public class friendsFragment extends Fragment {

    private View rootView;

    private TextView mjiahao;

    private leftListAdapter mleftListAdapter;
    private rightListAdapter mrightListAdapter;
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private List<String> leftDataList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_friends, container, false);

        leftRecyclerView=rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView=rootView.findViewById(R.id.rightRecyclerView);

        mjiahao=rootView.findViewById(R.id.jiahao);

        //添加朋友这里没有问题，可以正常添加数据到数据库
        rootView.findViewById(R.id.jiahao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), addFriends.class);
                startActivityForResult(intent,1000);
            }
        });
        return rootView;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        leftDataList.add("自己");
        leftDataList.add("特别关心");
        leftDataList.add("家人");
        leftDataList.add("同学");
        leftDataList.add("同事");
        mleftListAdapter=new leftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(mleftListAdapter);

        mrightListAdapter=new rightListAdapter();
        rightRecyclerView.setAdapter(mrightListAdapter);

        mrightListAdapter.setListData(DataService.getListData(getContext(),0));

        mrightListAdapter.setMonItemClickListener(new rightListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(FriendsInfo friendsInfo, int position) {
                Intent intent=new Intent(getActivity(), friendsDetails.class);
                intent.putExtra("friendsInfo",friendsInfo);
                startActivity(intent);
            }
        });

        mleftListAdapter.setmLeftListOnClickItemListner(new leftListAdapter.LeftListOnClickItemListner() {
            @Override
            public void onItemClick(int position) {
                mleftListAdapter.setCurrentIndex(position);
                mrightListAdapter.setListData(DataService.getListData(getContext(),position));
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            getActivity().finish();
            Intent intent=new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}