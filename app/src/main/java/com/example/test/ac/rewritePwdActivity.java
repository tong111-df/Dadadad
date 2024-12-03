package com.example.test.ac;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.db.UserDbHelper;
import com.example.test.entity.UserInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class rewritePwdActivity extends AppCompatActivity {

    private EditText et_newpwd,et_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rewrite_pwd);

        et_newpwd=findViewById(R.id.et_newpwd);
        et_confirm=findViewById(R.id.et_confirm);

        findViewById(R.id.btn_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_pwd=et_newpwd.getText().toString();
                String confirm=et_confirm.getText().toString();
                String svalidate=validatePassword(new_pwd);
                if(TextUtils.isEmpty(new_pwd)||TextUtils.isEmpty(confirm)){
                    Toast.makeText(rewritePwdActivity.this,"信息不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(!new_pwd.equals(confirm)){
                    Toast.makeText(rewritePwdActivity.this,"两次密码输入不同，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(svalidate.equals("密码强度足够。")) {
                        UserInfo userInfo = UserInfo.getsUserInfo();
                        if (userInfo != null) {
                            int row = UserDbHelper.getInstance(rewritePwdActivity.this).updatePwd(userInfo.getUsername(), new_pwd);
                            if (row > 0) {
                                Toast.makeText(rewritePwdActivity.this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                                //退出页面
                                setResult(1000);
                                finish();
                            } else {
                                Toast.makeText(rewritePwdActivity.this, "修改失败，请再次尝试", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(rewritePwdActivity.this, svalidate, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        findViewById(R.id.rewrite_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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