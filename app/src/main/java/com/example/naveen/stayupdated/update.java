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
import android.view.View;
import android.widget.EditText;

public class update extends AppCompatActivity {

/*    EditText old, newNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_update);
        old = (EditText) findViewById(R.id.old);
        newNum = (EditText) findViewById(R.id.newNum);

    }
    void updat(View view) {
        String method = "update";
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        BackgroundTask backgroundtask = new BackgroundTask(this);
        backgroundtask.execute("register", sharedPreferences.getString("Name", null), sharedPreferences.getString("PhoneNumber", null), sharedPreferences.getString("Address", null));
        backgroundtask.execute(method, old.getText().toString(), newNum.getText().toString());
        SharedPreferences.Editor e=sharedPreferences.edit();
        e.putString("PhoneNumber",newNum.getText().toString());
        startActivity(new Intent(this, Login.class));
    }*/
}


