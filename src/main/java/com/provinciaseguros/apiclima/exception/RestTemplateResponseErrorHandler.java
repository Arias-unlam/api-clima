package com.provinciaseguros.apiclima.exception;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (
                response.getStatusCode().series() == CLIENT_ERROR
                        || response.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        try {
            super.handleError(response);
        } catch (HttpStatusCodeException e) {

            String responseBody = e.getResponseBodyAsString();

            if (response.getStatusCode()
                    .series() == HttpStatus.Series.SERVER_ERROR) {
                log.info("Ocurrio un error interno: " + responseBody);
            } else if (response.getStatusCode()
                    .series() == HttpStatus.Series.CLIENT_ERROR) {
                log.info("Ocurrio un error en el cliente: " + responseBody);
                if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
                    ObjectMapper mapper = new ObjectMapper();
                    ErrorResponse errorResponse = mapper.readValue(responseBody, ErrorResponse.class);
                    throw new AccuWeatherException(errorResponse, e.getStatusCode(),  e.getMessage());
                }

                if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new NotFoundException();
                }
            }
        }
    }
}
