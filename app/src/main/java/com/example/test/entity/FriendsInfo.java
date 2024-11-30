package com.example.test.entity;

import java.io.Serializable;

public class FriendsInfo implements Serializable {
    private int friends_id;
    private String username;
    private int type_friends;
    private String friendsName;
    private String friendsDetails;

    public static FriendsInfo sfriendsInfo;

    public static FriendsInfo getSfriendsInfo() {
        return sfriendsInfo;
    }

    public static void setSfriendsInfo(FriendsInfo sfriendsInfo) {
        FriendsInfo.sfriendsInfo = sfriendsInfo;
    }

    public FriendsInfo(int friends_id,String username,int type_friends, String friendsName, String friendsDetails) {
        this.friends_id = friends_id;
        this.type_friends=type_friends;
        this.username=username;
        this.friendsName = friendsName;
        this.friendsDetails = friendsDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType_friends() {
        return type_friends;
    }

    public void setType_friends(int type_friends) {
        this.type_friends = type_friends;
    }

    public int getFriends_id() {
        return friends_id;
    }

    public void setFriends_id(int friends_id) {
        this.friends_id = friends_id;
    }

    public String getFriendsName() {
        return friendsName;
    }

    public void setFriendsName(String friendsName) {
        this.friendsName = friendsName;
    }

    public String getFriendsDetails() {
        return friendsDetails;
    }

    public void setFriendsDetails(String friendsDetails) {
        this.friendsDetails = friendsDetails;
    }
}
