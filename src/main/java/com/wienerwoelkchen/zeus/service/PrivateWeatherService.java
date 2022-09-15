package com.wienerwoelkchen.zeus.service;

import com.wienerwoelkchen.zeus.entity.PrivateWeather;
import com.wienerwoelkchen.zeus.util.ThingSpeakUriBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PrivateWeatherService {

    ThingSpeakUriBuilder thingSpeakUriBuilder = new ThingSpeakUriBuilder();
    String response;

    public PrivateWeather getCurrentWeather(String apiKey, String channelId) throws IOException {

        try {
            URL url = new URL(ThingSpeakUriBuilder.buildURIForThingSpeak(apiKey,channelId));

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            bufferedReader.close();

            response = stringBuilder.toString();

        } catch (IOException e) {
            return null;
        }

        PrivateWeather privateWeather = new PrivateWeather();

        try {
            org.json.JSONObject JSONObject = new JSONObject(response);
            privateWeather.setTemp(JSONObject.getJSONArray("feeds").getJSONObject(0).getDouble("field1"));
            privateWeather.setHumidity(JSONObject.getJSONArray("feeds").getJSONObject(0).getInt("field2"));

        } catch (JSONException | IndexOutOfBoundsException e) {
            return null;

        }

        return privateWeather;
    }
}
