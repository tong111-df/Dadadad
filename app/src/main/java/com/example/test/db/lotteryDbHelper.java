package com.example.test.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.test.entity.UserInfo;
import com.example.test.entity.lotteryInfo;
import com.example.test.fragment.lotteryFragment;

public class lotteryDbHelper extends SQLiteOpenHelper {
    private static lotteryDbHelper sHelper;
    private static final String DB_NAME = "lottery.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public lotteryDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static lotteryDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new lotteryDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建lottery_table表
        db.execSQL("create table lottery_table(lottery_id integer primary key autoincrement, " +
                "username text," + //用户名
                "dacnt integer," +
                "shicnt integer," +
                "zuicnt integer," +
                "bangcnt integer," +
                "decnt integer" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //增加每一个东西

    public int addlottery(String username,int dacnt,int shicnt,int zuicnt,int bangcnt,int decnt) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("dacnt", dacnt);
        values.put("shicnt", shicnt);
        values.put("zuicnt", zuicnt);
        values.put("bangcnt", bangcnt);
        values.put("decnt", decnt);
        String nullColumnHack = "values(null,?,?,?,?,?,?)";
        //执行
        int insert = (int) db.insert("lottery_table", nullColumnHack, values);
        //db.close();
        return insert;
    }


    /**
     * 根据用户唯一 lottery_id来修改da shi zui bang de
     */
    public int updateCnt(String username , int da,int shi,int zui,int bang,int de) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("dacnt", da);
        values.put("shicnt", shi);
        values.put("zuicnt", zui);
        values.put("bangcnt", bang);
        values.put("decnt", de);
        // 执行SQL
        int update = db.update("lottery_table", values, " username=?", new String[]{username});
        // 关闭数据库连接
        //db.close();
        return update;
    }


    //找到对应的唯一id
    @SuppressLint("Range")
    public lotteryInfo seek(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        lotteryInfo lottery_Info = null;
        String sql = "select lottery_id,username,dacnt,shicnt,zuicnt,bangcnt,decnt  from lottery_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int lottery_id = cursor.getInt(cursor.getColumnIndex("lottery_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int da = cursor.getInt(cursor.getColumnIndex("dacnt"));
            int shi = cursor.getInt(cursor.getColumnIndex("shicnt"));
            int zui = cursor.getInt(cursor.getColumnIndex("zuicnt"));
            int bang = cursor.getInt(cursor.getColumnIndex("bangcnt"));
            int de = cursor.getInt(cursor.getColumnIndex("decnt"));
            lottery_Info = new lotteryInfo(lottery_id, name, da, shi,zui,bang,de);
        }
        cursor.close();
        //db.close();
        return lottery_Info;
    }
}
