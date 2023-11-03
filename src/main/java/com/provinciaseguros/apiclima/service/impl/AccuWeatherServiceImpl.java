package com.provinciaseguros.apiclima.service.impl;

import com.provinciaseguros.apiclima.config.AccuWeatherConfiguration;
import com.provinciaseguros.apiclima.dto.LocationInfo;
import com.provinciaseguros.apiclima.dto.WeatherInfo;
import com.provinciaseguros.apiclima.service.AccuWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Slf4j
@Service
public class AccuWeatherServiceImpl implements AccuWeatherService {

    private final RestTemplate restTemplate;

    private final AccuWeatherConfiguration accuWeatherConfiguration;


    public AccuWeatherServiceImpl(@Autowired final RestTemplate restTemplate,
                                  @Autowired final AccuWeatherConfiguration accuWeatherConfiguration) {
        this.accuWeatherConfiguration = accuWeatherConfiguration;
        this.restTemplate = restTemplate;
    }

    public List<WeatherInfo> getWeatherForCity(String cityKey, String language, String metric)
            throws HttpStatusCodeException {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(accuWeatherConfiguration.getAccuweatherForeCastsUrl()
                        + cityKey)
                .queryParam("apikey", accuWeatherConfiguration.getApiKey())
                .queryParam("language", language)
                .queryParam("metric", metric)
                .cloneBuilder()
                .toUriString();

        return restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WeatherInfo>>(){}).getBody();
    }

    public LocationInfo getCityInfoByCityKey(String cityKey)
            throws HttpStatusCodeException {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(accuWeatherConfiguration.getAccuweatherLocationsUrl()
                        + cityKey)
                .queryParam("apikey", accuWeatherConfiguration.getApiKey())
                .cloneBuilder()
                .toUriString();

        return restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                null,
                LocationInfo.class).getBody();
    }

}
