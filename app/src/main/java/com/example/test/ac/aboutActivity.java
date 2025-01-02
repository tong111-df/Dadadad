package com.example.test.ac;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);

        findViewById(R.id.about_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
/*
* 2024.12.6灵光乍现，准备做iOS版，开始找虚拟机资源，找到虚拟机的镜像并进行下载，没在官网找到，只能在百度网盘下载，
* 2024.12.7在百度网盘下载，太慢了，狂看广告才下了一半左右
* 2024.12.8嗯，还是在百度网盘下载，终于还是下好了
* 2024.12.9配置好了Xcode环境，准备使用swiftUI+swift，正式开始，实现下一个伟大iOS软件诞生的第一步
* 2024.12.10swiftUI太难了，学习下Storyboard+swift，storyboard真好用，可以不用手搓控件直接配置，完成Mainboard的主体框架
* 2024.12.11完成了注册界面，忘记密码界面，以及登录界面的具体UI
* 2024.12.12完成了登录界面，可以实现账号密码登录，以及修改密码功能，使用CoreData进行数据存储，感觉比Android的SQlite好用
* 2024.12.13完成了主页八个小程序的功能，以及提出了朋友界面和抽奖界面不同于Android的构思
* 2024.12.14完成抽奖界面，与Android不同，采用转盘形式
* 2024.12.15完善抽奖界面，朋友界面被卡住了，不太好添加内容
* 2024.12.16试了一天依旧被卡住了，添加内容后始终无法保存显示
* 2024.12.17完成了朋友界面！！！发现之前添加的内容全部保存了但只是显示有问题，好在修复成功
* 2024.12.18增加了三万秒启动页面，打包成IPA文件

 */
