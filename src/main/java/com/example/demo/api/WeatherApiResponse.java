package com.example.demo.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class WeatherApiResponse {

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("alerts")
    private List<String> alerts;

    @SerializedName("city_name")
    private String cityName;

    private LocalDateTime date;
}
