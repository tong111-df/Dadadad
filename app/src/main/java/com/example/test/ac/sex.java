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

public class sex extends AppCompatActivity {

    private EditText et_your_sex;
    private ProgressBar progressBar_sex;
    private Button btn_query_sex;
    private Handler handler=new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sex);
        et_your_sex=findViewById(R.id.et_your_sex);
        btn_query_sex=findViewById(R.id.btn_query_sex);
        progressBar_sex = findViewById(R.id.progressBar_sex);
        findViewById(R.id.btn_query_sex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex = et_your_sex.getText().toString();
                if (TextUtils.isEmpty(sex)) {
                    Toast.makeText(sex.this, "请输入您的所在地", Toast.LENGTH_SHORT).show();
                } else {


                    progressBar_sex.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_sex.setVisibility(View.GONE);
                            showDialog(sex);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.sex_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void showDialog(String sex) {
        if(sex.equals("男")) {
            new AlertDialog.Builder(sex.this)
                    .setTitle("预测成功")
                    .setMessage("你喜欢女性")
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
        else if(sex.equals("女")) {
            new AlertDialog.Builder(sex.this)
                    .setTitle("预测成功")
                    .setMessage("你喜欢男性")
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
        else {
            new AlertDialog.Builder(sex.this)
                    .setTitle("预测成功")
                    .setMessage("你喜欢沃尔玛塑料袋吗？")
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
}