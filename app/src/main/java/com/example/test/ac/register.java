package com.example.test.ac;

import android.annotation.SuppressLint;
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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    private EditText etusername,etpassword,etconfirmpwd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        etusername=findViewById(R.id.et_re_user);
        etpassword=findViewById(R.id.et_re_pwd);
        etconfirmpwd=findViewById(R.id.et_re_confirm_pwd);

        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        findViewById(R.id.btn_registerok).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username,password,confirm;
                username=etusername.getText().toString();
                password=etpassword.getText().toString();
                confirm=etconfirmpwd.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(confirm.equals(password)) {
                        String svalidate=validatePassword(password);
                        if(svalidate.equals("密码强度足够。")) {
                            lotteryDbHelper.getInstance(register.this).addlottery(username, 0, 0, 0, 0, 0);
                            int row = UserDbHelper.getInstance(register.this).register(username, password, "无");
                            if (row > 0) {
                                Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(register.this, svalidate, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(register.this,"两次密码输入不同，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
    private static final Set<String> commonPasswords = new HashSet<>();
    public static String validatePassword(String password) {
        // 1. 检查密码长度
        if (password.length() < 8) {
            return "密码长度不足，至少需要 8 个字符。";
        }

        // 2. 检查是否为常见弱密码
        if (commonPasswords.contains(password)) {
            return "密码是常见密码，容易被猜测。";
        }

        // 3. 检查是否包含小写字母
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return "密码必须包含至少一个小写字母。";
        }

        // 4. 检查是否包含大写字母
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return "密码必须包含至少一个大写字母。";
        }

        // 5. 检查是否包含数字
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return "密码必须包含至少一个数字。";
        }

        // 6. 检查是否包含特殊字符
        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            return "密码必须包含至少一个特殊字符。";
        }

        // 8. 检查是否为纯数字或纯字母
        if (password.matches("[0-9]+") || password.matches("[a-zA-Z]+")) {
            return "密码不能是纯数字或纯字母。";
        }

        // 如果所有检查通过，返回密码强度足够
        return "密码强度足够。";
    }
}