package com.playground.demo.service;

import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate ;

    public HttpBinGetEntity testRestTemplateGetMethod() {

        String requestUrl = "https://httpbin.org/get";

        ResponseEntity<HttpBinGetEntity> response =
                restTemplate.exchange(requestUrl, HttpMethod.GET, RequestEntity.EMPTY , HttpBinGetEntity.class);

        log.info("GET HttpBin Response = {} ", response);

        return response.getBody();

    }

    public HttpBinGetEntity testRestTemplateGetMethod2() {

        String requestUrl = "https://httpbin.org/get";

        HttpBinGetEntity entity = restTemplate.getForObject(requestUrl, HttpBinGetEntity.class);

        log.info("GET HttpBin Entity = {} ", entity);

        return entity;

    }

    public ResponseCity testRestTemplatePostMethodCity(RequestCity requestCity) {

        String requestUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";

        HttpEntity<RequestCity> requestEntity = new HttpEntity<>(requestCity);

        ResponseEntity<ResponseCity> responseEntityCity = null;

        ResponseCity responseCity;

        try {

            responseEntityCity = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, ResponseCity.class);
            log.info("POST City Response: {}", responseEntityCity);

            responseCity = responseEntityCity.getBody();

        } catch (HttpClientErrorException ex){

            responseCity = ResponseCity.builder()
                    .msg(ex.getLocalizedMessage())
                    .error(true)
                    .build();

            log.error("City data not found. Error: {}", ex.getMessage() );

        }

        return responseCity;

    }

    public ResponseCity testRestTemplatePostMethodCity2(RequestCity requestCity) {

        String requestUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";

        ResponseCity responseCity;

        try {

            responseCity = restTemplate.postForObject(requestUrl, requestCity, ResponseCity.class);
            log.info("POST City: {}", responseCity);

        } catch (HttpClientErrorException ex){

            responseCity = ResponseCity.builder()
                    .msg(ex.getLocalizedMessage())
                    .error(true)
                    .build();

            log.error("City data not found. Error: {}", ex.getMessage() );

        }

        return responseCity;

    }

}
