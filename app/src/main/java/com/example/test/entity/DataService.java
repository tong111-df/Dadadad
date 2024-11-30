package com.example.test.entity;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.db.FriendDbHelper;

import java.util.ArrayList;
import java.util.List;

public class DataService  {
    public static List<FriendsInfo>getListData(Context context, int position){
        UserInfo userInfo = UserInfo.getsUserInfo();
        String username = userInfo.getUsername();
        //仿照继续写下去，主要就是如何才能同时都显示出来，待会去找下
        List<FriendsInfo> friendsList = new ArrayList<>();
       /* FriendsInfo seek1=FriendDbHelper.getInstance(context).seekFriends(username,1);
        FriendsInfo seek2=FriendDbHelper.getInstance(context).seekFriends(username,2);
        FriendsInfo seek3=FriendDbHelper.getInstance(context).seekFriends(username,3);
        FriendsInfo seek4=FriendDbHelper.getInstance(context).seekFriends(username,4);
       */
        List<FriendsInfo>friends0=FriendDbHelper.getInstance(context).seekFriends(username,0);
        List<FriendsInfo>friends1=FriendDbHelper.getInstance(context).seekFriends(username,1);
        List<FriendsInfo>friends2=FriendDbHelper.getInstance(context).seekFriends(username,2);
        List<FriendsInfo>friends3=FriendDbHelper.getInstance(context).seekFriends(username,3);
        List<FriendsInfo>friends4=FriendDbHelper.getInstance(context).seekFriends(username,4);
        if(position==0){
            friendsList.addAll(friends0);
        } else if (position == 1) {
            friendsList.addAll(friends1);
        } else if (position == 2) {
            friendsList.addAll(friends2);
        } else if (position == 3) {
            friendsList.addAll(friends3);
        } else if (position == 4) {
            friendsList.addAll(friends4);
        }
        return friendsList;
    }
}