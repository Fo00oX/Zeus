package com.wienerwoelkchen.zeus.controller;

import java.io.IOException;

import com.wienerwoelkchen.zeus.entity.PrivateWeather;
import com.wienerwoelkchen.zeus.service.PrivateWeatherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/private/weather")
public class PrivateWeatherController {

    PrivateWeatherService privateWeatherService = new PrivateWeatherService();

    @ResponseBody
    @GetMapping("/current/{channelid}/{apikey}")
    public ResponseEntity<PrivateWeather> currentWeather(@PathVariable String channelid, @PathVariable String apikey) {

        PrivateWeather currentWeather;
        try {
            currentWeather = privateWeatherService.getCurrentWeather(apikey,channelid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(currentWeather, HttpStatus.OK);
    }
}
