package com.provinciaseguros.apiclima.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccuWeatherException extends RuntimeException{

    private ErrorResponse errorResponse;

    private HttpStatus httpStatusCode;

    private String message;
}
