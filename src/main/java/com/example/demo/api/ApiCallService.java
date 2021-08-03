package com.example.demo.api;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiCallService {

    @CircuitBreaker(name = "dollarApi", fallbackMethod = "fallBackWeather")
    public WeatherApiResponse getWeather(Double lat,Double lon) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/alerts?lat="+lat+"&lon="+lon))
                .header("x-rapidapi-key", "730107e1d9msh918866ee264e089p1a002djsnc56f4f430645")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(),WeatherApiResponse.class);
    }


    public WeatherApiResponse fallBackWeather(final Throwable t){
        log.info("Fallback cause, {}",t.toString());
        return WeatherApiResponse.builder().build();
    }


}
