package com.sagensoren.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyStudent.db";
    public static final String TABLE_NAME = "MyStudent_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "COURSE_COUNT";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME+
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT ," +
                " EMAIL TEXT," +
                " COURSE_COUNT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String name, String email,String courseCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,courseCount);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1 ){
            return  false;

        }else {
            return true;
        }
    }

    public boolean updateData(String id, String name,String email, String courseCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,name);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,courseCount);

        db.update(TABLE_NAME,contentValues,"ID=?", new String[]{id});
        return true;

    }

    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?",new String[]{id});
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }



}
