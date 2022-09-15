package com.wienerwoelkchen.zeus.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ThingSpeakUriBuilder {
    static final String scheme = "https";
    static final String host = "api.thingspeak.com";
    static final String pathchannels = "/channels/";
    static final String pathfeeds= "/feeds.json";
    static final String results = "1";


    public static String buildURIForThingSpeak ( String apiKey, String channelId) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(scheme).host(host)
                .path(pathchannels+channelId+pathfeeds)
                .query("api_key={keyword}").query("results={keyword}")
                .buildAndExpand(apiKey,results);
        return uriComponents.toUriString();
    }
}
