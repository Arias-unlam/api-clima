package com.provinciaseguros.apiclima.controller;

import com.provinciaseguros.apiclima.exception.ErrorResponse;
import com.provinciaseguros.apiclima.exception.PathVariableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherControllerAdvice{


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                "Parametro faltante: " + paramName + " no puede ser nulo",
                null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PathVariableException.class)
    public ResponseEntity<Object> handleMissingPathVariables(PathVariableException ex){
        String variableName = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                "Parametro faltante: "+ variableName +" no puede ser nulo",
                null), HttpStatus.BAD_REQUEST);
    }

}
