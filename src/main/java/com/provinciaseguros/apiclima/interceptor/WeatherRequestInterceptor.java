package com.provinciaseguros.apiclima.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class WeatherRequestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        // Aquí se registra la información de la solicitud
        log.info("Solicitud saliente: " + httpRequest.getRequestLine());
    }
}