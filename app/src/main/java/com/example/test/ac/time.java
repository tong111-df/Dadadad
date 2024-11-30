package com.example.test.ac;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class time extends AppCompatActivity {

    private EditText et_your_time;
    private ProgressBar progressBar_time;
    private Button btn_query_time;
    private Handler handler=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time);

        et_your_time=findViewById(R.id.et_your_time);
        btn_query_time=findViewById(R.id.btn_query_time);
        progressBar_time = findViewById(R.id.progressBar_time);
        findViewById(R.id.btn_query_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = et_your_time.getText().toString();
                if (TextUtils.isEmpty(time)) {
                    Toast.makeText(time.this, "请输入您的所在地", Toast.LENGTH_SHORT).show();
                } else {


                    progressBar_time.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_time.setVisibility(View.GONE);
                            showDialog(time);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.time_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDialog(String time) {
        new AlertDialog.Builder(time.this)
                .setTitle("查询成功")
                .setMessage("现在时间为" + time)
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