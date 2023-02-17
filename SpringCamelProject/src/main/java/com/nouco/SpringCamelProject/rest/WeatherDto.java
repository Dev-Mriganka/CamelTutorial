package com.nouco.SpringCamelProject.rest;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    private int id;
    private String city;
    private String temperature;
    private String unit;
    private String receivedAt;
}
