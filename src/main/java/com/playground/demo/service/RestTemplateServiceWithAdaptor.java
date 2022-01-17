package com.playground.demo.service;

import com.playground.demo.adaptor.DemoAdaptor;
import com.playground.demo.adaptor.HttpBinAdaptor;
import com.playground.demo.adaptor.ResponseCityAdaptor;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateServiceWithAdaptor {

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private HttpBinAdaptor httpBinAdaptor;

    @Autowired
    private ResponseCityAdaptor responseCityAdaptor;

    public HttpBinGetEntity testRestTemplateGetMethod() {

        HttpBinGetEntity response = httpBinAdaptor.getHttpBinEntityFromUrl();

        log.info("GET HttpBin Entity = {} ", response);

        return response;

    }

    public ResponseCity testRestTemplatePostMethodCity(RequestCity requestCity) {

        String requestUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";

        ResponseCity responseCity = responseCityAdaptor.postForCityDetail(requestCity);

        log.info("POST for City: result = {}", responseCity);

        return responseCity;

    }

}
