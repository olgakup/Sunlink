package com.csun_sunlink.csuncareercenter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BgTask extends AsyncTask<String, Void, String> {

    private Context ctx;
    private View rootView;
    //IPV4:192.168.0.4

    private final String loginUrl = "http://10.0.2.2/csunsunlink/login.php";

    public BgTask(Context ctx, View rootView) {
        this.ctx = ctx;
        this.rootView = rootView;
    }

    @Override
    protected String doInBackground(String... params) {
        String method="", userPass="", response = "", line,userName="",emailAdd="";
        method = params[0];
        switch (method) {
            case "logIn":
                userName = params[1];
                userPass = params[2];
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    OutputStream os = urlConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                            URLEncoder.encode("userPass", "UTF-8") + "=" + URLEncoder.encode(userPass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream Is = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Is, "iso-8859-1"));
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    Is.close();
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        switch (result) {
            case "invalidUser":
                TextView errorText = (TextView) rootView.findViewById(R.id.signin_error);
                errorText.setText(R.string.invalid_userPass);
                break;
            case "validUser":
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
    }
}
