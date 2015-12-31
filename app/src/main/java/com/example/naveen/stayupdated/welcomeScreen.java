package com.example.naveen.stayupdated;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Button;

public class welcomeScreen extends AppCompatActivity {
    public String Myname,MyPhoneNumber,MyAddress;
    EditText uname,pass;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.welcomelayout);
    //    TransitionDrawable transition = (TransitionDrawable) relativeLayout.getBackground();
      //  transition.startTransition(3000);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

        public void loginuser(View view)
        {
            if(isNetworkAvailable()) {
                final String method = "login";
                String name = uname.getText().toString();
                String password = pass.getText().toString();
                BackgroundTask backgroundTask1 = new BackgroundTask(getApplicationContext());
                if ((backgroundTask1.execute(method, name, password)).equals("") == true)
                    Toast.makeText(getApplicationContext(), "Invalid user Details", Toast.LENGTH_LONG).show();
                else
                    startActivity(new Intent(getApplicationContext(), Login.class));
            }
            else{
                Toast.makeText(getApplicationContext(),"network not available",Toast.LENGTH_LONG);
            }
        }


    public void createNew(View view) {
        Context context = getApplicationContext();
        if(!isNetworkAvailable()){
            Toast toast = Toast.makeText(context,"network is not available right now...",Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            Intent intent= new Intent(this,createNew.class);
            startActivity(intent);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}