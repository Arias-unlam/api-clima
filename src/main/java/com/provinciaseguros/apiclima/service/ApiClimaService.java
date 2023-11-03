package com.provinciaseguros.apiclima.service;

import com.provinciaseguros.apiclima.dto.ClimaDataDTO;

import java.util.List;

public interface ApiClimaService {

    ClimaDataDTO serviceWeather(String cityCode, String language, String metric);
    List<ClimaDataDTO> auditResultList ();

}
