package com.example.naveen.stayupdated;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by sarthak on 08-11-2015.
 */
public class Login extends AppCompatActivity{
    TextView name,contact,addres;
    TextView a,b,c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name=(TextView)findViewById(R.id.name);
        contact=(TextView)findViewById(R.id.contact);
        addres=(TextView)findViewById(R.id.address);
        a=(TextView)findViewById(R.id.a);
        b=(TextView)findViewById(R.id.b);
        c=(TextView)findViewById(R.id.c);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        name.setText(sharedPreferences.getString("Name", null));
        contact.setText(sharedPreferences.getString("PhoneNumber", null));
        addres.setText(sharedPreferences.getString("Address", null));
    }
    public void onResume(Bundle savedInstanceState){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        name.setText(sharedPreferences.getString("Name", null));
        contact.setText(sharedPreferences.getString("PhoneNumber", null));
        addres.setText(sharedPreferences.getString("Address", null));
    }
    public void updatedata(View view)
    {
        Intent intent = new Intent(Login.this,update1.class);
        startActivity(intent);
        //startActivity(new Intent(this,update1.class));

    }
    public void search(View view)
    {
        startActivity(new Intent(this,main_page.class));
    }
}
