package com.example.sqliteinsert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gijs on 10/29/2017.
 */

public class myDbAdapter extends SQLiteOpenHelper
{
    public static final String LOG_TAG = myDbAdapter.class.getSimpleName();

    private static final String DATABASE_NAME = "users.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE "+ UserContract.UserEntity.TABLE_NAME +
            "( "+ UserContract.UserEntity.UID +" INTEGER PRIMARY KEY AUTOINCREMENT ," + UserContract.UserEntity.USER_NAME + " VARCHAR(225), " + UserContract.UserEntity.USER_PWD+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+ UserContract.UserEntity.TABLE_NAME;
    private Context context;

    public myDbAdapter(Context context) {
        super(context, UserContract.DATABASE_NAME, null, UserContract.DATABASE_VERSION);
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

