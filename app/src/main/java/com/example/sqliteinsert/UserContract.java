package com.example.sqliteinsert.data;

import android.provider.BaseColumns;

/**
 * Created by Marcel on 9-10-2017.
 */

/**Inner class that defines the table contents of the userinfo table. */
public final class UserContract implements BaseColumns {
    public final static String DATABASE_NAME = "employee";
    public final static int DATABASE_VERSION = 5;


    public final static class UserEntity{
        public final static String TABLE_NAME = "UserInfo";
        public final static String UID = BaseColumns._ID;
        public final static String USER_NAME = "UserName";
        public final static String USER_PWD = "Password";
    }
}
