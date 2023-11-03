package com.provinciaseguros.apiclima.mapper;

import com.provinciaseguros.apiclima.dto.ClimaDataDTO;
import com.provinciaseguros.apiclima.dto.LocationInfo;
import com.provinciaseguros.apiclima.dto.WeatherInfo;
import com.provinciaseguros.apiclima.model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {

    WeatherData map(ClimaDataDTO climaDataDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "locationInfo.country.localizedName")
    @Mapping(target = "city", source = "locationInfo.localizedName")
    @Mapping(target = "link", source = "weatherInfo.link")
    @Mapping(target = "temperature", expression = "java(weatherInfo.getTemperature().getValue() + weatherInfo.getTemperature().getUnit())")
    ClimaDataDTO toClimaDataDTO(LocationInfo locationInfo, WeatherInfo weatherInfo);

    List<ClimaDataDTO> map (List<WeatherData> weatherDataList);

}
