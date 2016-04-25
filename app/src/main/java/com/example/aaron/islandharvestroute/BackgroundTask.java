package com.example.aaron.islandharvestroute;

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
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Aaron on 4/20/2016.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;

    BackgroundTask(Context newContext) {
        context = newContext;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login status");
    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://10.0.2.2:8000/islandharvest/register.php";
        String login_url = "http://10.0.2.2:8000/islandharvest/login.php";

        String method = params[0];

        if(method.equals("register")) {
            String name = params[1];
            String user_name = params[2];
            String user_password = params[3];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                is.close();

                httpURLConnection.disconnect();

                return "Registration Success!";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            String loginName = params[1];
            String loginPass = params[2];

            try {
                URL url = new URL(login_url);
                URLConnection conn = url.openConnection();


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(loginName, "UTF-8") + "&"+
                        URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(loginPass, "UTF-8");


                bw.write(data);
                bw.flush();
                bw.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String response = "";
                String line = "";
                while((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
                is.close();
                httpURLConnection.disconnect();

                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return method;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equals("Registration Success!")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }


    }
}
