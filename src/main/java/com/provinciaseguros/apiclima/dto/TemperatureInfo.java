package com.provinciaseguros.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureInfo {
    @JsonProperty("Value")
    private double value;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("UnitType")
    private int unitType;
}
