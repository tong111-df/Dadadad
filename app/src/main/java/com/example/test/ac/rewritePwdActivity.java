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

                if(TextUtils.isEmpty(new_pwd)||TextUtils.isEmpty(confirm)){
                    Toast.makeText(rewritePwdActivity.this,"信息不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(!new_pwd.equals(confirm)){
                    Toast.makeText(rewritePwdActivity.this,"两次密码输入不同，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else{
                    UserInfo userInfo=UserInfo.getsUserInfo();
                    if(userInfo!=null){
                        int row = UserDbHelper.getInstance(rewritePwdActivity.this).updatePwd(userInfo.getUsername(), new_pwd);
                        if(row>0){
                            Toast.makeText(rewritePwdActivity.this,"密码修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                            //退出页面
                            setResult(1000);
                            finish();
                        }
                        else{
                            Toast.makeText(rewritePwdActivity.this,"修改失败，请再次尝试",Toast.LENGTH_SHORT).show();
                        }
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
}