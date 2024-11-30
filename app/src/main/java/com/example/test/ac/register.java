package com.example.test.ac;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.db.UserDbHelper;
import com.example.test.db.lotteryDbHelper;
import com.example.test.entity.UserInfo;

public class register extends AppCompatActivity {

    private EditText etusername,etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        etusername=findViewById(R.id.et_re_user);
        etpassword=findViewById(R.id.et_re_pwd);
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        findViewById(R.id.btn_registerok).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username,password;
                username=etusername.getText().toString();
                password=etpassword.getText().toString();
                if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                }
                else{
                    lotteryDbHelper.getInstance(register.this).addlottery(username, 0, 0, 0, 0, 0);
                    int row = UserDbHelper.getInstance(register.this).register(username, password, "无");
                    if(row>0){
                        Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });



    }
}