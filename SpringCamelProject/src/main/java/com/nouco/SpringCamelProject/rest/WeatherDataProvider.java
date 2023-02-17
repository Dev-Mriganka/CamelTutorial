package com.nouco.SpringCamelProject.rest;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDataProvider {

    private static Map<String, WeatherDto> weatherData = new HashMap<>();

    public WeatherDataProvider() {
        WeatherDto weatherDto = WeatherDto.builder().city("London").temperature("10").receivedAt(new Date().toString()).id(1).build();
        weatherData.put(weatherDto.getCity().toUpperCase(), weatherDto);
    }

    public WeatherDto getCurrentWeather(String city) {
        return weatherData.get(city.toUpperCase());
    }

    public void setCurrentWeather(WeatherDto weatherDto) {
        weatherDto.setReceivedAt(new Date().toString());
        weatherData.put(weatherDto.getCity().toUpperCase(), weatherDto);
    }

}
