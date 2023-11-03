package com.provinciaseguros.apiclima.service;

import com.provinciaseguros.apiclima.config.AccuWeatherConfiguration;
import com.provinciaseguros.apiclima.dto.LocationInfo;
import com.provinciaseguros.apiclima.dto.WeatherInfo;
import com.provinciaseguros.apiclima.service.impl.AccuWeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccuWeatherServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AccuWeatherConfiguration accuWeatherConfiguration;

    @InjectMocks
    private AccuWeatherServiceImpl accuWeatherService;

    @Test
    void testGetWeatherForCity() {
        String cityKey = "testCityKey";
        String language = "ex-mx";
        String metric = "true";

        String weatherUrl = "http://test-weather-api-url/";

        Mockito.when(accuWeatherConfiguration.getAccuweatherForeCastsUrl()).thenReturn(weatherUrl);
        Mockito.when(accuWeatherConfiguration.getApiKey()).thenReturn("testApiKey");


        List<WeatherInfo> mockWeatherInfo = Collections.singletonList(new WeatherInfo());
        ResponseEntity<List<WeatherInfo>> mockResponse = ResponseEntity.ok(mockWeatherInfo);
        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(),
                Mockito.any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        List<WeatherInfo> mockWeatherInfoService = Collections.singletonList(new WeatherInfo());

        List<WeatherInfo> result = accuWeatherService.getWeatherForCity(cityKey, language, metric);


        assertEquals(mockWeatherInfo, result);
    }

    @Test
    void testGetCityInfoByCityKey() {
        String cityKey = "testCityKey";
        String locationUrl = "http://test-location-api-url/";

        Mockito.when(accuWeatherConfiguration.getAccuweatherLocationsUrl()).thenReturn(locationUrl);
        Mockito.when(accuWeatherConfiguration.getApiKey()).thenReturn("testApiKey");

        LocationInfo mockLocationInfo = new LocationInfo(); // Mock objeto LocationInfo
        ResponseEntity<LocationInfo> mockResponse = ResponseEntity.ok(mockLocationInfo);
        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(),
                Mockito.eq(LocationInfo.class)
        )).thenReturn(mockResponse);

        LocationInfo result = accuWeatherService.getCityInfoByCityKey(cityKey);

        assertEquals(mockLocationInfo, result);
    }


}
