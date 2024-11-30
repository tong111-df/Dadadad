package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ac.MainActivity;
import com.example.test.ac.age;
import com.example.test.ac.bmi;
import com.example.test.ac.family;
import com.example.test.ac.namename;
import com.example.test.ac.sex;
import com.example.test.ac.temperature;
import com.example.test.ac.time;
import com.example.test.ac.weather;

public class homeFragment extends Fragment {


    private TextView tv_weather;
    private TextView tv_time;
    private TextView tv_family;
    private TextView tv_age;
    private TextView tv_temperature;
    private TextView tv_sex;
    private TextView tv_bmi;
    private TextView tv_namename;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_home, container, false);
        tv_weather=rootView.findViewById(R.id.tv_weather);
        tv_time=rootView.findViewById(R.id.tv_time);
        tv_family=rootView.findViewById(R.id.tv_family);
        tv_age=rootView.findViewById(R.id.tv_age);
        tv_temperature=rootView.findViewById(R.id.tv_temperature);
        tv_sex=rootView.findViewById(R.id.tv_sex);
        rootView.findViewById(R.id.tv_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), weather.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.tv_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), time.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.tv_namename).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), namename.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.tv_bmi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), bmi.class);
                startActivityForResult(intent,1000);
            }
        });
        rootView.findViewById(R.id.tv_sex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), sex.class);
                startActivityForResult(intent,1000);
            }
        });
        rootView.findViewById(R.id.tv_temperature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), temperature.class);
                startActivityForResult(intent,1000);
            }
        });
        rootView.findViewById(R.id.tv_family).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), family.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.tv_age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), age.class);
                startActivityForResult(intent,1000);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
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