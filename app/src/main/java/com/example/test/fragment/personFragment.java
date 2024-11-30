package com.example.test.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ac.aboutAPPActivity;
import com.example.test.ac.aboutActivity;
import com.example.test.ac.MainActivity;
import com.example.test.ac.callActivity;
import com.example.test.db.lotteryDbHelper;
import com.example.test.entity.UserInfo;
import com.example.test.ac.noticeActivity;
import com.example.test.ac.rewritePwdActivity;
import com.example.test.entity.lotteryInfo;

public class personFragment extends Fragment {

    private View rootView;

    private TextView tv_person_username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_person, container, false);

        tv_person_username= rootView.findViewById(R.id.tv_person_username);



       rootView.findViewById(R.id.tv_person_exit).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new AlertDialog.Builder(getContext())
                       .setTitle("温馨提示")
                       .setMessage("确定退出登录？")
                       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                       })
                       .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               getActivity().finish();
                               Intent intent=new Intent(getActivity(), MainActivity.class);
                               startActivity(intent);
                           }
                       })
                       .show();
           }
       });




       rootView.findViewById(R.id.revisepwd).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getActivity(), rewritePwdActivity.class);
               startActivityForResult(intent,1000);
           }
       });

        rootView.findViewById(R.id.notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), noticeActivity.class);
                startActivityForResult(intent,1000);
            }
        });


        rootView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), aboutActivity.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.aboutApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), aboutAPPActivity.class);
                startActivityForResult(intent,1000);
            }
        });


        rootView.findViewById(R.id.call_author).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), callActivity.class);
                startActivityForResult(intent,1000);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserInfo userInfo = UserInfo.getsUserInfo();

        if(userInfo!=null){
            tv_person_username.setText(userInfo.getUsername());
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            getActivity().finish();
            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }

}