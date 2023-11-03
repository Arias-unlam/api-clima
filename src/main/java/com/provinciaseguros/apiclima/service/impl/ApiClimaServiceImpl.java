package com.provinciaseguros.apiclima.service.impl;

import com.provinciaseguros.apiclima.dto.*;
import com.provinciaseguros.apiclima.mapper.WeatherDataMapper;
import com.provinciaseguros.apiclima.model.WeatherData;
import com.provinciaseguros.apiclima.repository.WeatherDataRepository;
import com.provinciaseguros.apiclima.service.AccuWeatherService;
import com.provinciaseguros.apiclima.service.ApiClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiClimaServiceImpl implements ApiClimaService {

    private final AccuWeatherService accuWeatherService;
    private final WeatherDataRepository weatherDataRepository;

    private final WeatherDataMapper weatherDataMapper;

    public ApiClimaServiceImpl(@Autowired WeatherDataRepository weatherDataRepository,
                               @Autowired AccuWeatherService accuWeatherService,
                               @Autowired WeatherDataMapper weatherDataMapper) {
        this.weatherDataRepository = weatherDataRepository;
        this.accuWeatherService = accuWeatherService;
        this.weatherDataMapper = weatherDataMapper;
    }

    @Override
    public ClimaDataDTO serviceWeather(String cityCode, String language, String metric) {

        List<WeatherInfo> weatherInfoList = accuWeatherService.getWeatherForCity(cityCode, language, metric);
        WeatherInfo weatherInfo = weatherInfoList.stream()
                .findFirst()
                .orElse(null);

        LocationInfo locationInfo = accuWeatherService.getCityInfoByCityKey(cityCode);

        ClimaDataDTO climaDataDTO = weatherDataMapper.toClimaDataDTO(locationInfo, weatherInfo);

        WeatherData weatherData = weatherDataMapper.map(climaDataDTO);

        weatherDataRepository.save(weatherData);

        return climaDataDTO;
    }

    public List<ClimaDataDTO> auditResultList () {
        List<WeatherData> weatherInfoList = weatherDataRepository.findAll();
        return weatherDataMapper.map(weatherInfoList);
    }

}
