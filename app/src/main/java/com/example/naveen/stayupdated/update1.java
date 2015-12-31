package com.example.naveen.stayupdated;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update1 extends AppCompatActivity {

    EditText old, newNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("appcreate","new activity started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_update);
        old = (EditText) findViewById(R.id.old);
        newNum = (EditText) findViewById(R.id.newNum);

    }
    public void updat(View view) {
        String method = "update";
        String updatednumber = newNum.getText().toString();
        String oldnumber = old.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
       // Log.d("appcreate", sharedPreferences.getString("Address", null));
        BackgroundTask backgroundtask = new BackgroundTask(this);
        if (oldnumber.equals((String) sharedPreferences.getString("PhoneNumber", null))) {
            backgroundtask.execute("register", sharedPreferences.getString("Name", null), updatednumber, sharedPreferences.getString("Address", null));
            BackgroundTask bt = new BackgroundTask(this);
            bt.execute(method,oldnumber,updatednumber);
            SharedPreferences.Editor e=sharedPreferences.edit();
             e.putString("PhoneNumber",newNum.getText().toString());
             e.apply();
        } else {
            Toast.makeText(getApplicationContext(), "Kindly enter your correct old number", Toast.LENGTH_LONG).show();
        }
    }
       // startActivity(new Intent(this, Login.class));}
}


