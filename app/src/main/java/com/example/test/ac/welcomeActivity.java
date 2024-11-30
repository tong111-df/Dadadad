package com.example.test.ac;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class welcomeActivity extends AppCompatActivity {

    private TextView tvCountdown;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 4000; // 设置倒计时时长，单位为毫秒

    public welcomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        tvCountdown=findViewById(R.id.tv_countdown);

        startCountdown();

    }


    private void startCountdown() {
        countDownTimer =new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                tvCountdown.setText(secondsRemaining +" s");
            }

            @Override
            public void onFinish() {
                //跳转到登录页面（看自己逻辑想跳转哪个页面）
                startActivity(new Intent(welcomeActivity.this, MainActivity.class));
                // 倒计时结束后的操作，例如跳转到主页面
                finish();

            }
        }.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


}