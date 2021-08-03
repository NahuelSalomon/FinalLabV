package com.example.demo.controller;

import com.example.demo.api.WeatherApiResponse;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/now")
    public ResponseEntity<WeatherApiResponse> getWeatherNow(@RequestParam(defaultValue = "38.5") Double lat,@RequestParam(defaultValue = "-78.5") Double log) throws IOException, InterruptedException {
        WeatherApiResponse weather = weatherService.getWeatherNow(lat,log);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }

}
