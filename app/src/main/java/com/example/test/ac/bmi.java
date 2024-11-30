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

public class bmi extends AppCompatActivity {

    private EditText et_your_height;
    private EditText et_your_weight;
    private ProgressBar progressBar_bmi;
    private Button btn_query_bmi;
    private Handler handler=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);

        et_your_height=findViewById(R.id.et_your_height);
        et_your_weight=findViewById(R.id.et_your_weight);
        btn_query_bmi=findViewById(R.id.btn_query_bmi);
        progressBar_bmi = findViewById(R.id.progressBar_bmi);


        findViewById(R.id.btn_query_bmi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = et_your_height.getText().toString();
                String weight = et_your_weight.getText().toString();
                boolean isDouble=false;
                try {
                    Double value = Double.parseDouble(height);
                    Double valuee = Double.parseDouble(weight);
                    isDouble = true;
                } catch (NumberFormatException e) {
                    isDouble = false;
                }
                if (TextUtils.isEmpty(height)||TextUtils.isEmpty(weight)||!isDouble) {
                    Toast.makeText(bmi.this, "请正确输入您的身高和体重", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar_bmi.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_bmi.setVisibility(View.GONE);
                            showDialog(height,weight);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.bmi_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void showDialog(String height,String weight) {
        Double h=Double.parseDouble(height);
        Double w=Double.parseDouble(weight);
        Double bmi = Math.round(w / (h * h) * 100.0) / 100.0;
        String ss=Double.toString(bmi);
        new AlertDialog.Builder(bmi.this)
                .setTitle("计算成功")
                .setMessage("您的BMI为" + ss)
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