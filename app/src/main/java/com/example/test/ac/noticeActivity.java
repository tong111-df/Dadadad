package com.example.test.ac;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class noticeActivity extends AppCompatActivity {
    private Button myButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notice);

        myButton = findViewById(R.id.myButton);
        updateButtonVisibility();


        findViewById(R.id.notice_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =null;
                intent=new Intent(noticeActivity.this, Easter_egg.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_notice_advertisement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =null;
                intent=new Intent(noticeActivity.this, advertisement2.class);
                startActivity(intent);
            }
        });
    }

    private void updateButtonVisibility() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentTime = sdf.format(calendar.getTime());


        String timePoint1 = "05:45";
        String timePoint2 = "08:45";


        if (currentTime.equals(timePoint1) || currentTime.equals(timePoint2)) {
            myButton.setVisibility(View.VISIBLE);
        } else {
            myButton.setVisibility(View.GONE);
        }
    }
}