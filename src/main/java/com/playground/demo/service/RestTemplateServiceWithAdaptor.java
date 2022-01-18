package com.playground.demo.service;

import com.playground.demo.adaptor.HttpBinAdaptor;
import com.playground.demo.adaptor.ResponseCityAdaptor;
import com.playground.demo.model.CityResponse;
import com.playground.demo.model.HttpBinGetResponse;
import com.playground.demo.model.RequestCity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
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

    @Cacheable(value = "httpBin", cacheManager = "customCacheManager")
    public HttpBinGetResponse testRestTemplateGetMethod() {

        HttpBinGetResponse response = httpBinAdaptor.getHttpBinEntityFromUrl();

        log.info("GET HttpBin Entity = {} ", response);

        return response;

    }

    @Cacheable(value = "responseCity", key = "#requestCity.getCity()", unless = "#result.error", cacheManager = "customCacheManager")
    public CityResponse testRestTemplatePostMethodCity(RequestCity requestCity) {

        CityResponse cityResponse = responseCityAdaptor.postForCityDetail(requestCity);

        log.info("POST for City: result = {}", cityResponse);

        return cityResponse;

    }

    @CacheEvict(value = "responseCity", key="#requestCity.getCity()", cacheManager = "customCacheManager")
    public void clearResponseCityCache(RequestCity requestCity){
        log.info("Clear Cache {}", requestCity);
    }

    @CachePut(value = "responseCity", key = "#requestCityReal.getCity()", cacheManager = "customCacheManager")
    public CityResponse troubleMaking(RequestCity requestCityReal, RequestCity requestCityFake) {

        CityResponse cityResponse = responseCityAdaptor.postForCityDetail(requestCityFake);

        log.info("POST for City: result = {}", cityResponse);

        log.info("Return fake: {}, \ninstead of the real one: {}", requestCityFake, requestCityReal);
        return cityResponse;
    }
}
