package com.example.lahiru.webapp;

import android.app.AlertDialog;
import android.content.Context;
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

import javax.net.ssl.HttpsURLConnection;

public class BackgroundTask extends AsyncTask<String,Void,String>{
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String regURL = "http://192.168.43.111/webapp/register.php";
        String logURL = "http://192.168.43.111/webapp/login.php";
        String method = strings[0];
        if(method.equals("register")){
            String category = strings[1];
            String name = strings[2];
            String shopName = strings[3];
            String price = strings[4];
            try {
                URL url = new URL(regURL);
                HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                OutputStream os = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8")+ "&" +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+ "&" +URLEncoder.encode("shop_name","UTF-8")+"="+URLEncoder.encode(shopName,"UTF-8")+ "&" +URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpsURLConnection.getInputStream();
                is.close();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(method.equals("login")){
            String user_name = strings[1];
            String user_pass = strings[2];
            try {
                URL url = new URL(logURL);
                HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream os = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
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
        alertDialog.setTitle("Login Information");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration Success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        } else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}
