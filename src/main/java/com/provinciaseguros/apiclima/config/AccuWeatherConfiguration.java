package com.provinciaseguros.apiclima.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api-clima.accuweather")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccuWeatherConfiguration {

    private String apiKey;

    private String accuweatherForeCastsUrl;

    private String accuweatherLocationsUrl;

}
