package com.provinciaseguros.apiclima.config;

import com.provinciaseguros.apiclima.exception.RestTemplateResponseErrorHandler;
import com.provinciaseguros.apiclima.interceptor.WeatherRequestInterceptor;
import com.provinciaseguros.apiclima.interceptor.WeatherResponseInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Configuration
public class RestTemplateConfig {

    private final WeatherRequestInterceptor weatherRequestInterceptor;

    private final WeatherResponseInterceptor weatherResponseInterceptor;

    public RestTemplateConfig(@Autowired WeatherRequestInterceptor weatherRequestInterceptor,
                              @Autowired WeatherResponseInterceptor weatherResponseInterceptor) {
        this.weatherRequestInterceptor = weatherRequestInterceptor;
        this.weatherResponseInterceptor = weatherResponseInterceptor;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(new MyRequestFactorySupplier())
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    class MyRequestFactorySupplier implements Supplier<ClientHttpRequestFactory> {
        @Override
        public ClientHttpRequestFactory get() {
            CloseableHttpClient client = HttpClientBuilder.create()
                    .addInterceptorFirst(weatherRequestInterceptor)
                    .addInterceptorLast(weatherResponseInterceptor)
                    .build();
            return new HttpComponentsClientHttpRequestFactory(client);
        }
    }
}