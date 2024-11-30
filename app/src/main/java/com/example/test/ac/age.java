package com.example.test.ac;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;

import java.util.Calendar;

public class age extends AppCompatActivity {

    private EditText et_your_age;
    private ProgressBar progressBar_age;
    private Button btn_query_age;
    private Handler handler=new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_age);
        et_your_age=findViewById(R.id.et_your_age);
        btn_query_age=findViewById(R.id.btn_query_age);
        progressBar_age = findViewById(R.id.progressBar_age);
        findViewById(R.id.btn_query_age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = et_your_age.getText().toString().trim();
                boolean isInteger=false;
                try {
                    int value = Integer.parseInt(age);
                    isInteger = true;
                } catch (NumberFormatException e) {
                    isInteger = false;
                }
                if (TextUtils.isEmpty(age)||!isInteger) {
                    Toast.makeText(age.this, "请正确输入您的出生年份", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar_age.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_age.setVisibility(View.GONE);
                            showDialog(age);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.age_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    private void showDialog(String age) {
        int s=Integer.parseInt(age);
        int m=year-s;
        String aage=Integer.toString(m);
        new AlertDialog.Builder(age.this)
                .setTitle("计算成功")
                .setMessage("您的年龄为" + aage)
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