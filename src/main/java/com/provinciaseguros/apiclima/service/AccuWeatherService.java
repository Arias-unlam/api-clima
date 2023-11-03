package com.provinciaseguros.apiclima.service;

import com.provinciaseguros.apiclima.dto.LocationInfo;
import com.provinciaseguros.apiclima.dto.WeatherInfo;

import java.util.List;

public interface AccuWeatherService {
    List<WeatherInfo> getWeatherForCity(String city, String language, String metric);

    LocationInfo getCityInfoByCityKey(String cityKey);
}

