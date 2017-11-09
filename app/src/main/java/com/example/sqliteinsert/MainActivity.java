package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass, deleteName,currentName,newName;
    myDbAdapter helper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.view_data);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, view_data.class);
                startActivity(intent);
            }
        });

        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        currentName=(EditText)findViewById(R.id.currentName);
        newName = (EditText)findViewById(R.id.newName);
        deleteName = (EditText) findViewById(R.id.deleteUserName);
        helper = new myDbAdapter(this);
        context = this;
    }

    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put(UserContract.UserEntry.USER_NAME,t1);
            cv.put(UserContract.UserEntry.USER_PWD,t2);
            Uri newUri = getContentResolver().insert(UserContract.UserEntry.CONTENT_URI,cv);
            if (newUri== null) {
                Message.message(this, getString(R.string.unsuccessful));
            }
            else{
                Name.setText("");
                Pass.setText("");
                Message.message(this, getString(R.string.successful));
                }
            }
    public void delete(View view){
        String name = deleteName.getText().toString();
        if(name.length()>0) {
            Uri uri = UserContract.UserEntry.CONTENT_URI.buildUpon().appendPath(name).build();
            int i = getContentResolver().delete(uri, null, null);
            if (i < 1) {
                Message.message(this, getString(R.string.deleteUnsuccessful));
            } else {
                Message.message(this, getString(R.string.deleteSuccessful));
            }
        }
    }
    public void update(View view){
        String CurrentName= currentName.getText().toString();
        String NewName= newName.getText().toString();
        Uri uri = UserContract.UserEntry.CONTENT_URI.buildUpon().appendPath(CurrentName).build();
        if(currentName.length()>0&&newName.length()>0) {
            if (!currentName.equals(newName)) {
                ContentValues cv = new ContentValues();
                cv.put("Name", NewName);
                int i = getContentResolver().update(uri, cv, null, null);
                if(i<1) Message.message(this, String.format(getString(R.string.no_user_found), currentName));
                else Message.message(this,getString(R.string.update_succesful));
            }
        }
    }
}
