/*
* 2024.11.21完成Android studio环境配置，实现下一个伟大软件诞生的第一步
* 2024.11.22完成登录基本界面设计，初步了解as的各个模块
* 2024.11.23完成登录界面，账号密码用数据库存放，尽管现在可能只是作为一个应用锁的功能，记得尝试下能否连接别人手机通过开发者模式得到数据库里面内容来找回密码
* 2024.11.24完成底部导航，可以实现从底部导航进入不同功能页面，创建好友页面的左侧选择列表，准备在个人中心添加修改密码，软件说明，联系方式
* 2024.11.25完成个人中心页面，记得将以上和以下记录在关于APP中，新增加3s启动页面，完成抽奖的数据库的添加
* 2024.11.26尝试了一天的lottery数据库，本以为能完成抽奖功能，但23：45才意识到不太对，应该和user数据库放在同一个下面，但所幸今天完成了不用数据库的抽奖功能
* 2024.11.27我太强了，又花了一天把lottery完整写好，可以实现倒计时功能，设置最少等待1h才能继续抽奖，可以将各项保存到数据库中，同时在数据库中读取，lottery真正意义上的自己独立制作的毫无参考的模块，太激动了
* 2024.11.28完成了主页的基本构思，完成了主页的天气预报，创立了朋友界面的添加朋友，创建了朋友数据库
* 2024.11.29完成了主页面的小程序添加，朋友界面可以展示朋友列表，同时可以实现点击详情以及修改功能，17：55正式结束，19：00正式完成
* */


package com.example.test.ac;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.db.UserDbHelper;
import com.example.test.entity.UserInfo;

public class MainActivity extends AppCompatActivity {

    private Button btnlogin,btnregister,btnforgetpwd,btnsave;
    private EditText etuser,etpassword;
    private CheckBox checkbox;
    private boolean is_login;

    private SharedPreferences msharedpreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuser=findViewById(R.id.et_1);
        etpassword=findViewById(R.id.et_2);
        checkbox=findViewById(R.id.check_box);

        msharedpreferences=getSharedPreferences("user",MODE_PRIVATE);

        is_login = msharedpreferences.getBoolean("is_login", false);
        if(is_login){
            String username = msharedpreferences.getString("username", null);
            String password = msharedpreferences.getString("password", null);
            etuser.setText(username);
            etpassword.setText(password);
            checkbox.setChecked(true);
        }


        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =null;
                intent=new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_forgetpwd).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent =null;
                intent=new Intent(MainActivity.this, forgetActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etuser.getText().toString();
                String password=etpassword.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"请输入账号或密码",Toast.LENGTH_SHORT).show();
                }
                else{
                    UserInfo login = UserDbHelper.getInstance(MainActivity.this).login(username);
                    if(login!=null){
                        if(username.equals(login.getUsername())&&password.equals(login.getPassword())){

                            SharedPreferences.Editor edit = msharedpreferences.edit();
                            edit.putBoolean("is_login",is_login);
                            edit.putString("username",username);
                            edit.putString("password",password);
                            edit.commit();

                            UserInfo.setsUserInfo(login);

                            Intent intent =null;
                            intent=new Intent(MainActivity.this, function.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"该账号尚未注册",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login=isChecked;
            }
        });
    }

}