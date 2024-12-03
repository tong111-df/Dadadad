package com.example.test.ac;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.example.test.db.FriendDbHelper;
import com.example.test.entity.FriendsInfo;
import com.example.test.entity.UserInfo;

public class friendsDetails extends AppCompatActivity {

    private TextView et_de_friendsName;
    private EditText et_de_friendsDetails;
    private EditText et_de_friendsType;
    private FriendsInfo friendsInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friends_details);

        friendsInfo=(FriendsInfo)getIntent().getSerializableExtra("friendsInfo");

        UserInfo userInfo = UserInfo.getsUserInfo();
        String username = userInfo.getUsername();
        et_de_friendsName=findViewById(R.id.et_de_friendsName);
        et_de_friendsDetails=findViewById(R.id.et_de_friendsDetails);
        et_de_friendsType=findViewById(R.id.et_de_friendsType);

        if(null!=friendsInfo){
            et_de_friendsName.setText(friendsInfo.getFriendsName());
            et_de_friendsDetails.setText(friendsInfo.getFriendsDetails());
            et_de_friendsType.setText(Integer.toString(friendsInfo.getType_friends()));
        }
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeString=et_de_friendsType.getText().toString();
                String name=et_de_friendsName.getText().toString();
                String details=et_de_friendsDetails.getText().toString();
                if((typeString.equals("0"))||(typeString.equals("1"))||(typeString.equals("2"))||(typeString.equals("3"))||(typeString.equals("4"))){
                    int type = Integer.parseInt(typeString);
                    FriendDbHelper.getInstance(friendsDetails.this).updateFriends(username, type, name, details);
                    Toast.makeText(friendsDetails.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                }
                else {
                    Toast.makeText(friendsDetails.this,"请在类别中输入具体数字",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.datails_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}