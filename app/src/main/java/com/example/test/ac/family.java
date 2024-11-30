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

public class family extends AppCompatActivity {

    private EditText et_your_family;
    private ProgressBar progressBar_family;
    private Button btn_query_family;
    private Handler handler=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_family);

        et_your_family=findViewById(R.id.et_your_family);
        btn_query_family=findViewById(R.id.btn_query_family);
        progressBar_family = findViewById(R.id.progressBar_family);
        findViewById(R.id.btn_query_family).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String family = et_your_family.getText().toString();
                if (TextUtils.isEmpty(family)) {
                    Toast.makeText(family.this, "请输入您的所在地", Toast.LENGTH_SHORT).show();
                } else {


                    progressBar_family.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_family.setVisibility(View.GONE);
                            showDialog(family);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.family_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void showDialog(String family) {
        new AlertDialog.Builder(family.this)
                .setTitle("计算成功")
                .setMessage("你应该管他叫亲戚")
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