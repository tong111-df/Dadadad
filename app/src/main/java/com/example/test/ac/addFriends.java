package com.example.test.ac;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.example.test.db.FriendDbHelper;
import com.example.test.entity.UserInfo;

public class addFriends extends AppCompatActivity {
    private EditText et_ad_friendsName;
    private EditText et_ad_friendsDetails;
    private EditText et_ad_friendsType;
    private Button btn_sure;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_friends);

        UserInfo userInfo = UserInfo.getsUserInfo();
        String username = userInfo.getUsername();
        et_ad_friendsName=findViewById(R.id.et_ad_friendsName);
        et_ad_friendsDetails=findViewById(R.id.et_ad_friendsDetails);
        et_ad_friendsType=findViewById(R.id.et_ad_friendsType);
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeString=et_ad_friendsType.getText().toString();
                String name=et_ad_friendsName.getText().toString();
                String details=et_ad_friendsDetails.getText().toString();
                if((typeString.equals("0"))||(typeString.equals("1"))||(typeString.equals("2"))||(typeString.equals("3"))||(typeString.equals("4"))){
                    int type = Integer.parseInt(typeString);
                    int row = FriendDbHelper.getInstance(addFriends.this).addfriends(username, type, name, details);
                    if (row > 1) {
                        Toast.makeText(addFriends.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else {
                    Toast.makeText(addFriends.this,"请在类别中输入具体数字",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.addfriends_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}