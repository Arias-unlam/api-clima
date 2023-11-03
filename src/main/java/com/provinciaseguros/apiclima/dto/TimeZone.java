package com.provinciaseguros.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeZone {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("GmtOffset")
    private double gmtOffset;
    @JsonProperty("IsDaylightSaving")
    private boolean isDaylightSaving;
    @JsonProperty("NextOffsetChange")
    private String nextOffsetChange;
}
