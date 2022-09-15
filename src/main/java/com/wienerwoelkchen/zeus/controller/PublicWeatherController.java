package com.wienerwoelkchen.zeus.controller;

import java.io.IOException;

import com.wienerwoelkchen.zeus.entity.PublicWeather;
import com.wienerwoelkchen.zeus.service.PublicWeatherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/weather")
public class PublicWeatherController {

    PublicWeatherService publicWeatherService = new PublicWeatherService();

    @ResponseBody
    @GetMapping("/current/{requestLocation}")
    public ResponseEntity<PublicWeather> currentWeather(@PathVariable String requestLocation) {

        PublicWeather currentWeather;
        try {
            currentWeather = publicWeatherService.getCurrentWeather(requestLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(currentWeather, HttpStatus.OK);
    }
}
