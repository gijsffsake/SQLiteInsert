package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.sqliteinsert.UserContract.UserEntry;

public class view_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {

        String[] projection = {
                UserEntry._ID,
                UserEntry.USER_NAME,
                UserEntry.USER_PWD};

        Cursor cursor = getContentResolver().query(
                UserEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_users);

        try {
            displayView.setText("The UserInfo table contains " + cursor.getCount() + " users.\n\n");
            displayView.append(UserEntry._ID + " - " +
                    UserEntry.USER_NAME + " - " +
                    UserEntry.USER_PWD + "\n");

            int idColumnIndex = cursor.getColumnIndex(UserEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(UserEntry.USER_NAME);
            int passwordColumnIndex = cursor.getColumnIndex(UserEntry.USER_PWD);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPassword = cursor.getString(passwordColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPassword));
            }
        } finally {
            cursor.close();
        }
    }

    //private void insertUser() {
//
    //    ContentValues values = new ContentValues();
    //    values.put(UserEntry.USER_NAME, "test");
    //    values.put(UserEntry.USER_PWD, "test");
//
    //    Uri newUri = getContentResolver().insert(UserEntry.CONTENT_URI, values);
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}


