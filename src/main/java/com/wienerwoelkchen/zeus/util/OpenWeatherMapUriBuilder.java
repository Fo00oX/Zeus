package com.wienerwoelkchen.zeus.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class OpenWeatherMapUriBuilder {

    final String scheme = "https";
    final String host = "api.openweathermap.org";
    final String path = "/data/2.5/weather";
    final String queryUnits = "metric";
    final String apiKey = "7aef3abfc813165a5d253adbfa398a6e";

    public final String setURIForCity(String queryCityName) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(scheme).host(host)
                .path(path)
                .query("q={keyword}").query("units={keyword}").query("appid={keyword}")
                .buildAndExpand(queryCityName, queryUnits, apiKey);

        return uriComponents.toUriString();
    }
}

