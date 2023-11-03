package com.provinciaseguros.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Elevation {
    @JsonProperty("Metric")
    private Measurement metric;
    @JsonProperty("Imperial")
    private Measurement imperial;
}
