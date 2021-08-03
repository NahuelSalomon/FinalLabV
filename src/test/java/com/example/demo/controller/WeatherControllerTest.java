package com.example.demo.controller;
import com.example.demo.api.WeatherApiResponse;
import com.example.demo.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WeatherControllerTest {

    private WeatherService weatherService;
    private WeatherController weatherController;


    @BeforeEach
    public void setUp() {
        this.weatherService = mock(WeatherService.class);
        this.weatherController = new WeatherController(weatherService);
    }

    @Test
    public void getWeatherNow() {

        try {
            WeatherApiResponse weatherApiResponse = WeatherApiResponse.builder().build();
            when(weatherService.getWeatherNow(any(),any())).thenReturn(weatherApiResponse);
            ResponseEntity<WeatherApiResponse> responseEntity = weatherController.getWeatherNow(60.0,60.0);
            assertEquals(weatherApiResponse,responseEntity.getBody());
            assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());

        } catch (IOException | InterruptedException e) {
            fail(e);
        }
    }
}
