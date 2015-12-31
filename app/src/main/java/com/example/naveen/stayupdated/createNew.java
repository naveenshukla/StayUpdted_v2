package com.example.naveen.stayupdated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
/*import android.widget.Toolbar;*/


public class createNew extends AppCompatActivity {
    public String name = null, contact = null, address = null, method="register";
    public EditText contactName, contactPhoneNumber, contactAddress;
    public Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_new);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SaveMyContact(View view) {
//        taking text with editText
        contactName = (EditText) findViewById(R.id.editName);
        contactPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
        contactAddress = (EditText) findViewById(R.id.editAddress);
        name = contactName.getText().toString();
        contact = contactPhoneNumber.getText().toString();
        address = contactAddress.getText().toString();


        //saving data locally (via SharedPreferences)
        final Context context = getBaseContext();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString("Name", name);
        e.putString("PhoneNumber", contact);
        e.putString("Address", address);
        e.commit();

        final String Myname = sharedPreferences.getString("Name", null);
        final String MyPhoneNumber = sharedPreferences.getString("PhoneNumber", null);
        final String MyAddress = sharedPreferences.getString("Address", null);


        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,Myname,MyPhoneNumber,MyAddress);


        //popup dialogue box for making entry whether it is correct or no( via alertdialogueBuilder)
        AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(this);
        if(name.isEmpty() || contact.isEmpty() || address.isEmpty()){
            alertDialogueBuilder.setMessage("You haven't provided all info...");
            alertDialogueBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
        }
        else {
            alertDialogueBuilder.setMessage("Everything is set, you are good to go");
            alertDialogueBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                    Log.d("appcreate", "new activity is going to start");
                    BackgroundTask backgroundTask1 = new BackgroundTask(getApplicationContext());
                    backgroundTask1.execute(method,Myname,MyPhoneNumber,MyAddress);
                    startActivity(new Intent(((Dialog) dialog).getContext(), welcomeScreen.class));
                }
            });
        }
        AlertDialog alertDialogue = alertDialogueBuilder.create();
        alertDialogue.show();
    }
}
