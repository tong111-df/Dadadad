package com.example.test.ac;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;

public class weather extends AppCompatActivity {

    private EditText et_your_wea;

    private ProgressBar progressBar;
    private Button btn_query;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);


        et_your_wea=findViewById(R.id.et_your_wea);
        btn_query=findViewById(R.id.btn_query);
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wea=et_your_wea.getText().toString();
                if(TextUtils.isEmpty(wea)){
                    Toast.makeText(weather.this,"请输入您的所在地",Toast.LENGTH_SHORT).show();
                }
                else{


                    progressBar.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar.setVisibility(View.GONE);
                            showDialog(wea);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }
        });

        findViewById(R.id.weather_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void showDialog(String wea) {
        new AlertDialog.Builder(weather.this)
                .setTitle("查询成功")
                .setMessage("该地" + "天气为" + wea + "的天气")
                .setNegativeButton("感谢大", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("非常感谢大", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}