package com.example.demo.service;

import com.example.demo.api.ApiCallService;
import com.example.demo.api.WeatherApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    private ApiCallService apiCallService;
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        apiCallService = mock(ApiCallService.class);
        weatherService = new WeatherService(apiCallService);
    }

    @Test
    public void getWeatherNowOk() {
        try {
            WeatherApiResponse weatherApiResponse = WeatherApiResponse.builder().build();
            when(apiCallService.getWeather(any(),any())).thenReturn(weatherApiResponse);

            WeatherApiResponse weatherApiResponse1 = weatherService.getWeatherNow(60.0,60.0);

            assertEquals(weatherApiResponse,weatherApiResponse1);

            verify(apiCallService,times(1)).getWeather(60.0,60.0);
        }catch (IOException | InterruptedException e) {
            fail(e);
        }
    }

}
