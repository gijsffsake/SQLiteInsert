package com.example.sqliteinsert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sqliteinsert.UserContract.DATABASE_NAME;
import static com.example.sqliteinsert.UserContract.DATABASE_VERSION;
import static com.example.sqliteinsert.UserContract.UserEntry.TABLE_NAME;
import static com.example.sqliteinsert.UserContract.UserEntry.UID;

/**
 * Created by Gijs on 10/29/2017.
 */

public class myDbAdapter extends SQLiteOpenHelper
{

    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
            "( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT ," + UserContract.UserEntry.USER_NAME+ " VARCHAR(225)," + UserContract.UserEntry.USER_PWD+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+ TABLE_NAME;
    myDbAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    }
}

