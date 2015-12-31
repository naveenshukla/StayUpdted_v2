package com.example.naveen.stayupdated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naveen on 1/11/15.
 */
 public class BackgroundTask extends AsyncTask<String, Void, String> {
    public Activity activity;
    public String json_string = null;
    Context ctx;
    public boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL("http://www.google.co.uk");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    BackgroundTask(Context ctx,Activity activity){
        this.activity = activity;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://oneconnect.blogjist.com/register.php";
        String search_url = "http://oneconnect.blogjist.com/search.php";
        String login_url="http://oneconnect.blogjist.com/login.php";
        String update_url="http://oneconnect.blogjist.com/update.php";
        String method = params[0];
        if(method.equals("register")){
            String name = params[1];
            String contact = params[2];
            String address = params[3];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    httpURLConnection.disconnect();
                    return "Successs ...";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        if(method.equals("update")){
            String  oldnumber= params[1];
            String newcontact = params[2];
            try {
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("oldnumber", "UTF-8") + "=" + URLEncoder.encode(oldnumber, "UTF-8") + "&" +
                        URLEncoder.encode("newcontact", "UTF-8") + "=" + URLEncoder.encode(newcontact, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Successs ...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
          if(method.equals("login")) {
            String name = params[1];
            String contact = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                while ((json_string = br.readLine()) != null) {
                    line += json_string;
                }
                OS.close();
                inputStream.close();
                httpURLConnection.disconnect();
                json_string=line;
                return "Successs ...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("search")){
            String oldnumber = params[1];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("oldnumber","UTF-8") +"="+ URLEncoder.encode(oldnumber,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

               InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String urlString="";
                while ((json_string = bufferedReader.readLine())!=null){
                    urlString += json_string;
                }
                /*bufferedReader.close();
                inputStream.close();*/
                /*httpURLConnection.disconnect();*/
                json_string = urlString;
                Log.d("appcreate",json_string);
                Log.d("appcreate",urlString);
                //showonscreen(urlString);
                return urlString;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*Log.d("appcreate","doInBackground for search is working properly");*/
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Successs ...")){

            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();

        }
        else if(result.equals("{\"result\":[]}")){
            TextView textViewname = (TextView) this.activity.findViewById(R.id.textviewname);
            textViewname.setText("Looks like this user hasn't" +
                    " created contact on ContactHub");
        }
       else {
            List<String> allNames = new ArrayList<String>();
            List<String> allAddress = new ArrayList<String>();
            List<String> allContact = new ArrayList<String>();
            TextView textViewname = (TextView) this.activity.findViewById(R.id.textviewname);
            TextView textViewcontact = (TextView) this.activity.findViewById(R.id.viewphonenumber);
            TextView textViewaddress = (TextView) this.activity.findViewById(R.id.viewaddress);
            try {
                JSONObject mainObject = new JSONObject(result);
                JSONArray cast = mainObject.getJSONArray("result");
                Log.d("appcreate", String.valueOf(cast.length()));
                for (int i = 0; i < cast.length(); i++) {
                    JSONObject user = cast.getJSONObject(i);
                    String username = user.getString("name");
                    String usercontact = user.getString("contact");
                    String useraddress = user.getString("address");
                    allAddress.add(useraddress);
                    allContact.add(usercontact);
                    allNames.add(username);
                }
                String first = "This word is ";
                String next = "<font color='#EE0000'>red</font>";
                textViewname.setText("Name : " + allNames.get(0));
                textViewaddress.setText("Address : " + allAddress.get(0));
                textViewcontact.setText("Contact : " + allContact.get(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public String returnJSON(String result){
        json_string = result;
        return json_string;
    }
}

