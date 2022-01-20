package com.playground.demo.service;

import com.playground.demo.adaptor.HttpBinAdaptor;
import com.playground.demo.adaptor.ResponseCityAdaptor;
import com.playground.demo.model.response.CityResponse;
import com.playground.demo.model.response.HttpBinGetResponse;
import com.playground.demo.model.request.CityRequest;
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

    @Cacheable(value = "cityResponse", key = "#cityRequest.getCity()", unless = "#result.error", cacheManager = "customCacheManager")
    public CityResponse testRestTemplatePostMethodCity(CityRequest cityRequest) {

        CityResponse cityResponse = responseCityAdaptor.postForCityDetail(cityRequest);

        log.info("POST for City: result = {}", cityResponse);

        return cityResponse;

    }

    @CacheEvict(value = "cityResponse", key="#cityRequest.getCity()", cacheManager = "customCacheManager")
    public void clearResponseCityCache(CityRequest cityRequest){
        log.info("Clear Cache {}", cityRequest);
    }

    @CachePut(value = "cityResponse", key = "#cityRequestReal.getCity()", cacheManager = "customCacheManager")
    public CityResponse troubleMaking(CityRequest cityRequestReal, CityRequest cityRequestFake) {

        CityResponse cityResponse = responseCityAdaptor.postForCityDetail(cityRequestFake);

        log.info("POST for City: result = {}", cityResponse);

        log.info("Return fake: {}, \ninstead of the real one: {}", cityRequestFake, cityRequestReal);
        return cityResponse;
    }
}
