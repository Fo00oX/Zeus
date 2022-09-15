package com.wienerwoelkchen.zeus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wienerwoelkchen.zeus.util.OpenWeatherMapUriBuilder;
import com.wienerwoelkchen.zeus.entity.PublicWeather;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PublicWeatherService {

    OpenWeatherMapUriBuilder openWeatherMapUriBuilder = new OpenWeatherMapUriBuilder();
    String response;

    public PublicWeather getCurrentWeather(String requestLocation) throws IOException {

        try {
            URL url = new URL(openWeatherMapUriBuilder.setURIForCity(requestLocation));

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

        PublicWeather publicWeather = new PublicWeather();

        try {
            org.json.JSONObject JSONObject = new JSONObject(response);
            publicWeather.setResponseLocation(JSONObject.getString("name"));
            publicWeather.setDescription(JSONObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            publicWeather.setTemp(JSONObject.getJSONObject("main").getDouble("temp"));
            publicWeather.setFeels_like (JSONObject.getJSONObject("main").getDouble("feels_like"));
            publicWeather.setTemp_max(JSONObject.getJSONObject("main").getDouble("temp_max"));
            publicWeather.setTemp_min(JSONObject.getJSONObject("main").getDouble("temp_min"));

        } catch (JSONException | IndexOutOfBoundsException e) {
            return null;

        }

        return publicWeather;
    }
}

