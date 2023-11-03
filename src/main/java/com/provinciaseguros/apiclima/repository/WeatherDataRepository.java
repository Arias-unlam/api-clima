package com.provinciaseguros.apiclima.repository;

import com.provinciaseguros.apiclima.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

}
