package com.example.weatherapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeather extends AsyncTask<String, Void, String> {

    Activity _activity;
    public CurrentWeather(Activity activity) {
        this._activity = activity;
    }

    String result ="";
    @Override
    protected String doInBackground(String... city) {

        try {
            String formedUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city[0] +"&APPID=7cfbf674d3192253ad4a2e16feae3f1e";
            URL url = new URL(formedUrl);
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
            String weather = jsonObject.getString("weather");
            JSONArray jsonArray = new JSONArray(weather);
            JSONObject mainWeather = jsonArray.getJSONObject(0);
            String description = mainWeather.getString("description");
            TextView weatherReport = (TextView) this._activity.findViewById(R.id.weatherReport);
            weatherReport.setText(description);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
