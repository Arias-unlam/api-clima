package com.provinciaseguros.apiclima;

import com.provinciaseguros.apiclima.controller.WeatherController;
import com.provinciaseguros.apiclima.service.AccuWeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ApiClimaApplicationTests {

	MockMvc mockMvc;

	@InjectMocks
	WeatherController weatherController;

	@MockBean
	AccuWeatherService accuWeatherService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(this.weatherController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void WHEN_get_temperature_1hour_by_city_with_bad_request_THEN_the_status_is_400()  {

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api-clima/city/4337")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
