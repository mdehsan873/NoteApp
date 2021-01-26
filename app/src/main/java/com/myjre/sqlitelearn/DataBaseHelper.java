package com.myjre.sqlitelearn;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String tablename="decsmsg";
    public static String id="_id";
    public static String subject="subject";
    public static String desc="description";
    public static String DB_NAME="monu.db";
    public static int DB_VERSION=1;
    Application application;


    //creating quary
    public static String Create_Table="create table "+ tablename +"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            subject +" TEXT NOT NULL, "+ desc +" TEXT);";

    public DataBaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+tablename);
    onCreate(db);
    }
}
