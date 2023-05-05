package com.chemaev.service;

import com.chemaev.util.OpenWeatherUtil;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherService {

    public String getWeather(String city) {
        String weatherJSON;
        try {
            weatherJSON = (String) OpenWeatherUtil.getWeather(city);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject obj = new JSONObject(weatherJSON);
        double temperature = obj.getJSONObject("main").getDouble("temp");

        System.out.printf("Temperature in %s is %s", city, temperature - 273.15);
        return weatherJSON;
    }
}

