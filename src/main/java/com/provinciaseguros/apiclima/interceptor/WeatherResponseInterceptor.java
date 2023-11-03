package com.provinciaseguros.apiclima.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeatherResponseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse response, HttpContext context) {
        // Aquí se registra la información de la respuesta
        log.info("Respuesta recibida: " + response.getStatusLine());
    }
}
