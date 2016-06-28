package com.example.lahiru.smartshopfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SearchTask extends AsyncTask<String,Void,String> {
    public static String finalresult = null;
    AlertDialog alertDialog;
    Context ctx;
    SearchTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String searchURL = "http://192.168.43.111/webapp/search.php";
        String method = strings[0];
        if(method.equals("search")){
            String name = strings[1];
            String price = strings[2];
            String category = strings[3];
            String time = strings[4];
            String latitude = strings[5];
            String longitude = strings[6];
            try {
                URL url = new URL(searchURL);
                HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                OutputStream os = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+ "&" +URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+ "&" +URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8")+ "&" +URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+ "&" +URLEncoder.encode("lat1","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+ "&" +URLEncoder.encode("lon1","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response += line;
                }
                bufferedReader.close();
                is.close();
                httpsURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Search Information");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        finalresult = result;
        //MapsActivity mapsActivity = new MapsActivity(result);
        //viewAct(ctx);
    }
/*
    public static void viewAct(Context ctx){
        ctx.startActivity(new Intent(ctx, MapsActivity.class));
    }
    */
}

