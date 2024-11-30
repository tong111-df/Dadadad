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

public class temperature extends AppCompatActivity {

    private EditText et_your_temperature;
    private ProgressBar progressBar_temperature;
    private Button btn_query_temperature;
    private Handler handler=new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature);

        et_your_temperature=findViewById(R.id.et_your_temperature);
        btn_query_temperature=findViewById(R.id.btn_query_temperature);
        progressBar_temperature = findViewById(R.id.progressBar_temperature);
        findViewById(R.id.btn_query_temperature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temperature = et_your_temperature.getText().toString();
                boolean isDouble=false;
                try {
                    Double value = Double.parseDouble(temperature);
                    isDouble = true;
                } catch (NumberFormatException e) {
                    isDouble = false;
                }

                if (TextUtils.isEmpty(temperature)||!isDouble) {
                    Toast.makeText(temperature.this, "请正确输入华氏度", Toast.LENGTH_SHORT).show();
                } else {


                    progressBar_temperature.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_temperature.setVisibility(View.GONE);
                            showDialog(temperature);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.temperature_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void showDialog(String temperature) {
        Double s=Double.parseDouble(temperature);
        Double x=(s-32)*5/9;
        double roundedNumber = Math.round(x * 100.0) / 100.0;
        String ss=Double.toString(roundedNumber);
        new AlertDialog.Builder(temperature.this)

                .setTitle("计算成功")
                .setMessage("其为" + ss + "°C")
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