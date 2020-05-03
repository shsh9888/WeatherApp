package com.example.weatherapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeather extends AsyncTask<String, Void, String> {

    String result ="";
    @Override
    protected String doInBackground(String... city) {

        try {
            //http://api.openweathermap.org/data/2.5/weather?q=Boulder&APPID=7cfbf674d3192253ad4a2e16feae3f1e
            URL url = new URL("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
            HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                result += (char)data;
                data=reader.read();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute (String result) {
        //Method gets executed after doInBackground
        try {
            JSONObject jsonObject = new JSONObject(result);
            jsonObject.getString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
