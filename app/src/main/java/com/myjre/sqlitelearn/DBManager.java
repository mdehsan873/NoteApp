package com.myjre.sqlitelearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){

        context=c;
    }

    public DBManager open() throws SQLException{
        dbHelper=new DataBaseHelper(context);
        database=dbHelper.getReadableDatabase();
        database=dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String name,String Dec){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper.subject,name);
        contentValues.put(DataBaseHelper.desc,Dec);
        database.insert(DataBaseHelper.tablename,null,contentValues);
    }
    public  Cursor fetch(){
        String[] columns=new String[]{DataBaseHelper.id,DataBaseHelper.subject,DataBaseHelper.desc};
        Cursor cursor=database.query(DataBaseHelper.tablename,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long id,String name,String dec){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper.subject,name);
        contentValues.put(DataBaseHelper.desc,dec);
        int i=database.update(DataBaseHelper.tablename,contentValues,DataBaseHelper.id+" == "+ id,null);
        return i;
    }
    public void delete(long id){
        database.delete(DataBaseHelper.tablename,DataBaseHelper.id+" == "+id,null);
    }
}
