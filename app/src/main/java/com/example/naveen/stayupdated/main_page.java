package com.example.naveen.stayupdated;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.provider.ContactsContract;

import org.w3c.dom.Text;

public class main_page extends AppCompatActivity {
    public  Context context;
    public String Myname,MyPhoneNumber,MyAddress;
    public String searchText;
    public SearchView searchView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        context = getApplicationContext();

        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setOnSearchClickListener(hello);
        searchView.setOnQueryTextListener(byeee);

       /* textView = (TextView)findViewById(R.id.searViewResults);*/


        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        Myname = sharedPreferences.getString("Name", null);
        MyPhoneNumber = sharedPreferences.getString("PhoneNumber", null);
        MyAddress = sharedPreferences.getString("Address", null);
    }


    View.OnClickListener hello = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Log.d("appcreate","Search Key is pressed");
        }
    };


    SearchView.OnQueryTextListener byeee = new SearchView.OnQueryTextListener(){
        @Override
        public boolean onQueryTextSubmit(String query) {
            String method = "search";
            String newquery = query.substring(1);
            if(isNumeric(newquery)){
                BackgroundTask backgroundTask = new BackgroundTask(context,main_page.this);
                backgroundTask.execute(method, query);
            }
            else {
                String ret = getPhoneNumber(query, getApplicationContext());
                String newstring = ret.replaceAll("\\s+", "");
                if (newstring.charAt(0) == '0') {
                    newstring = newstring.substring(1);
                    Log.d("appcreate", newstring);
                } else if (newstring.charAt(0) == '+' && newstring.charAt(1) == '9' && newstring.charAt(2) == '1') {
                    newstring = newstring.substring(3);
                    Log.d("appcreate", newstring);
                }
                BackgroundTask backgroundTask = new BackgroundTask(context, main_page.this);
                backgroundTask.execute(method, newstring);
            }
            return false;
        }
        public boolean isNumeric(String str)
        {
            try
            {
                double d = Double.parseDouble(str);
            }
            catch(NumberFormatException nfe)
            {
                return false;
            }
            return true;
        }
        public String getPhoneNumber(String name, Context context) {
            String ret = null;
            String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
            String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projection, selection, null, null);
            if (c.moveToFirst()) {
                ret = c.getString(0);
            }
            c.close();
            if(ret==null)
                ret = "Unsaved";
            return ret;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if(newText.equals("")){
                TextView textView = (TextView)findViewById(R.id.viewaddress);
                TextView textView1 = (TextView)findViewById(R.id.viewphonenumber);
                TextView textView2 = (TextView)findViewById(R.id.textviewname);
                textView.setText(" ");
                textView1.setText(" ");
                textView2.setText(" ");
            }
            return false;
        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_with_userinfo, menu);
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
        if(id==R.id.user_info){
            AlertDialog.Builder myProfileDialogue = new AlertDialog.Builder(this);
            myProfileDialogue.setMessage(Myname + "\n" + MyPhoneNumber + "\n" +  MyAddress + "\n");
            myProfileDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            myProfileDialogue.setNegativeButton("Update", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    startActivity(new Intent(((Dialog) dialog).getContext(), update1.class));
                }
            });
            AlertDialog profileDialogue = myProfileDialogue.create();
            profileDialogue.show();

        }

        return super.onOptionsItemSelected(item);
    }
}