package com.provinciaseguros.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherInfo {
    @JsonProperty("DateTime")
    private String dateTime;

    @JsonProperty("EpochDateTime")
    private long epochDateTime;

    @JsonProperty("WeatherIcon")
    private int weatherIcon;

    @JsonProperty("IconPhrase")
    private String iconPhrase;

    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;

    @JsonProperty("IsDayLight")
    private boolean isDaylight;

    @JsonProperty("Temperature")
    private TemperatureInfo temperature;

    @JsonProperty("PrecipitationProbability")
    private int precipitationProbability;

    @JsonProperty("MobileLink")
    private String mobileLink;

    @JsonProperty("Link")
    private String link;
}

