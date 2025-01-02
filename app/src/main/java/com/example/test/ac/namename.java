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

public class namename extends AppCompatActivity {

    private EditText et_your_lastname;
    private EditText et_your_firstname;
    private ProgressBar progressBar_namename;
    private Button btn_query_namename;
    private Handler handler=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_namename);

        et_your_lastname=findViewById(R.id.et_your_lastname);
        et_your_firstname=findViewById(R.id.et_your_firstname);
        btn_query_namename=findViewById(R.id.btn_query_namename);
        progressBar_namename = findViewById(R.id.progressBar_namename);

        findViewById(R.id.btn_query_namename).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastname = et_your_lastname.getText().toString();
                String firstname = et_your_firstname.getText().toString();

                if (TextUtils.isEmpty(lastname)||TextUtils.isEmpty(firstname)) {
                    Toast.makeText(namename.this, "请输入您的姓和名", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar_namename.setVisibility(View.VISIBLE);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // 延迟后在这里显示对话框
                            progressBar_namename.setVisibility(View.GONE);
                            showDialog(lastname,firstname);
                        }
                    };
                    // 延迟3秒（3000毫秒）后执行runnable
                    handler.postDelayed(runnable, 3000);

                }
            }

        });

        findViewById(R.id.namename_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void showDialog(String lastname,String firstname) {
        if (lastname.equals("李") && firstname.equals("梓萌")) {
            new AlertDialog.Builder(namename.this)
                    .setTitle("计算成功")
                    .setMessage("您是美女" + lastname + firstname)
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
        }  else if (lastname.equals("佟") && firstname.equals("权健")) {
            new AlertDialog.Builder(namename.this)
                    .setTitle("计算成功")
                    .setMessage("您是尊贵的的大")
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
        }else if (lastname.equals("吴") && firstname.equals("仕博")) {
            new AlertDialog.Builder(namename.this)
                    .setTitle("计算成功")
                    .setMessage("您应该是帅气的" + lastname + firstname)
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
        } else{
            new AlertDialog.Builder(namename.this)
                    .setTitle("计算成功")
                    .setMessage("您的姓名为" + lastname + firstname)
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



