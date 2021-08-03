package com.example.demo.service;

import com.example.demo.api.ApiCallService;
import com.example.demo.api.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final ApiCallService apiCallService;

    @Autowired
    public WeatherService(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    public WeatherApiResponse getWeatherNow(Double lat, Double log) throws IOException, InterruptedException {
        WeatherApiResponse weatherApiResponse = apiCallService.getWeather(lat, log);
        weatherApiResponse.setDate(LocalDateTime.now());
        return weatherApiResponse;
    }

}
