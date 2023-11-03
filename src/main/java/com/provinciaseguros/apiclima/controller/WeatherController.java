package com.provinciaseguros.apiclima.controller;

import com.provinciaseguros.apiclima.dto.ClimaDataDTO;
import com.provinciaseguros.apiclima.exception.AccuWeatherException;
import com.provinciaseguros.apiclima.exception.PathVariableException;
import com.provinciaseguros.apiclima.service.ApiClimaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-clima")
@Api(tags = "Weather API", description = "Endpoints for Weather Information")
public class WeatherController {

    private final ApiClimaService apiClimaService;

    public WeatherController(@Autowired ApiClimaService apiClimaService) {
        this.apiClimaService = apiClimaService;
    }


    @GetMapping("/city/{cityCode}")
    @ApiOperation(value = "Get Weather Information by City Code",
            notes = "Retrieve hourly weather information for a specific city",
            response = ClimaDataDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved weather information"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "City code not found")
    })
    public ResponseEntity<Object> getCodeCity(@PathVariable(required = true) String cityCode,
                                              @RequestParam(required = true) String language,
                                              @RequestParam(required = true) String metric) {
        if(cityCode == null){
            throw new PathVariableException("cityCode");
        }

        try {
            ClimaDataDTO climaDataDTO = apiClimaService.serviceWeather(cityCode, language, metric);
            return new ResponseEntity<>(climaDataDTO, HttpStatus.OK);
        } catch (AccuWeatherException e){
            return ResponseEntity.status(e.getHttpStatusCode()).body(e.getErrorResponse());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener los datos del clima", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/audit-result-list")
    @ApiOperation(value = "Get Audit Result List",
            notes = "Retrieve the audit results list",
            response = ClimaDataDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the audit results list"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Object> getAuditResultList(){
        try{
            List<ClimaDataDTO> climaDataDTOList = apiClimaService.auditResultList();
            return new ResponseEntity<>(climaDataDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
