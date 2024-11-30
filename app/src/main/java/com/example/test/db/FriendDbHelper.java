package com.example.test.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.test.entity.FriendsInfo;
import com.example.test.entity.lotteryInfo;

import java.util.ArrayList;
import java.util.List;

public class FriendDbHelper extends SQLiteOpenHelper {
    private static FriendDbHelper sHelper;
    private static final String DB_NAME = "Friend.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public FriendDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static FriendDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new FriendDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建lottery_table表
        db.execSQL("create table friend_table(friends_id integer primary key autoincrement, " +
                "username text," + //用户名
                "type_friends integer," +
                "friendsName text," +
                "friendsDetails text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //增加每一个东西

    public int addfriends(String username,int type_friends,String friendsName,String friendsDetails) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("type_friends", type_friends);
        values.put("friendsName", friendsName);
        values.put("friendsDetails", friendsDetails);

        String nullColumnHack = "values(null,?,?,?,?)";
        //执行
        int insert = (int) db.insert("friend_table", nullColumnHack, values);
        //db.close();
        return insert;
    }


    /**
     * 根据用户唯一 user_id来修改da shi zui bang de
     */
    public int updateFriends(String username, int type_friends,String friendsName,String friendsDetails) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("type_friends", type_friends);
        values.put("friendsDetails", friendsDetails);
        // 执行SQL
        int update = db.update("friend_table", values, " username=?AND friendsName=?", new String[]{username,friendsName});
        // 关闭数据库连接
        //db.close();
        return update;
    }


    //找到对应的唯一id
    @SuppressLint("Range")
    public List<FriendsInfo> seekFriends(String username, int type_friends) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();

        List<FriendsInfo>friendsList=new ArrayList<>();
        String sql = "select friends_id,username,type_friends,friendsName,friendsDetails  from friend_table where username=? AND type_friends=?";
        String[] selectionArgs = {username,String.valueOf(type_friends)};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int friends_id = cursor.getInt(cursor.getColumnIndex("friends_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int friendsType = cursor.getInt(cursor.getColumnIndex("type_friends"));
            String friendsName = cursor.getString(cursor.getColumnIndex("friendsName"));
            String friendsDetails= cursor.getString(cursor.getColumnIndex("friendsDetails"));
            FriendsInfo friendsInfo = null;
            friendsInfo=new FriendsInfo(friends_id,name,friendsType,friendsName,friendsDetails);
            friendsList.add(friendsInfo);
        }
        cursor.close();
        //db.close();
        return friendsList;
    }

}
