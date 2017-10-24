package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


static class myDbHelper extends SQLiteOpenHelper
{
    private static final String CREATE_TABLE = "CREATE TABLE "+ com.example.sqliteinsert.data.UserContract.UserEntity.TABLE_NAME +
            "( "+ com.example.sqliteinsert.data.UserContract.UserEntity.UID +" INTEGER PRIMARY KEY AUTOINCREMENT ," + com.example.sqliteinsert.data.UserContract.UserEntity.USER_NAME + " VARCHAR(225), " + com.example.sqliteinsert.data.UserContract.UserEntity.USER_PWD+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+ com.example.sqliteinsert.data.UserContract.UserEntity.TABLE_NAME;
    private Context context;

    public myDbHelper(Context context) {
        super(context, com.example.sqliteinsert.data.UserContract.DATABASE_NAME, null, com.example.sqliteinsert.data.UserContract.DATABASE_VERSION);
        this.context=context;
        Message.message(context,"Started...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(CREATE_TABLE);
            Message.message(context,"TABLE CREATED");
        } catch (Exception e) {
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}

