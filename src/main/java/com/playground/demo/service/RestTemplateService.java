package com.playground.demo.service;

import com.playground.demo.model.CityResponse;
import com.playground.demo.model.HttpBinGetResponse;
import com.playground.demo.model.RequestCity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate ;

    public HttpBinGetResponse testRestTemplateGetMethod() {

        String requestUrl = "https://httpbin.org/get";

        ResponseEntity<HttpBinGetResponse> response =
                restTemplate.exchange(requestUrl, HttpMethod.GET, RequestEntity.EMPTY , HttpBinGetResponse.class);

        log.info("GET HttpBin Response = {} ", response);

        return response.getBody();

    }

    public HttpBinGetResponse testRestTemplateGetMethod2() {

        String requestUrl = "https://httpbin.org/get";

        HttpBinGetResponse entity = restTemplate.getForObject(requestUrl, HttpBinGetResponse.class);

        log.info("GET HttpBin Entity = {} ", entity);

        return entity;

    }

    public CityResponse testRestTemplatePostMethodCity(RequestCity requestCity) {

        String requestUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";

        HttpEntity<RequestCity> requestEntity = new HttpEntity<>(requestCity);

        ResponseEntity<CityResponse> responseEntityCity = null;

        CityResponse cityResponse;

        try {

            responseEntityCity = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, CityResponse.class);
            log.info("POST City Response: {}", responseEntityCity);

            cityResponse = responseEntityCity.getBody();

        } catch (HttpClientErrorException ex){

            cityResponse = CityResponse.builder()
                    .msg(ex.getLocalizedMessage())
                    .error(true)
                    .build();

            log.error("City data not found. Error: {}", ex.getMessage() );

        }

        return cityResponse;

    }

    public CityResponse testRestTemplatePostMethodCity2(RequestCity requestCity) {

        String requestUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";

        CityResponse cityResponse;

        try {

            cityResponse = restTemplate.postForObject(requestUrl, requestCity, CityResponse.class);
            log.info("POST City: {}", cityResponse);

        } catch (HttpClientErrorException ex){

            cityResponse = CityResponse.builder()
                    .msg(ex.getLocalizedMessage())
                    .error(true)
                    .build();

            log.error("City data not found. Error: {}", ex.getMessage() );

        }

        return cityResponse;

    }

}
