package com.playground.demo.service;

import com.playground.demo.adaptor.HttpBinAdaptor;
import com.playground.demo.adaptor.ResponseCityAdaptor;
import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
    public HttpBinGetEntity testRestTemplateGetMethod() {

        HttpBinGetEntity response = httpBinAdaptor.getHttpBinEntityFromUrl();

        log.info("GET HttpBin Entity = {} ", response);

        return response;

    }

    @Cacheable(value = "responseCity", key = "#requestCity", unless = "#result.error", cacheManager = "customCacheManager")
    public ResponseCity testRestTemplatePostMethodCity(RequestCity requestCity) {

        ResponseCity responseCity = responseCityAdaptor.postForCityDetail(requestCity);

        log.info("POST for City: result = {}", responseCity);

        return responseCity;

    }

    @CacheEvict(value = "responseCity", key="#requestCity", cacheManager = "customCacheManager")
    public void clearResponseCityCache(RequestCity requestCity){
        log.info("Clear Cache {}", requestCity);
    }

    @CachePut(value = "responseCity", key = "#requestCityReal", cacheManager = "customCacheManager")
    public ResponseCity troubleMaking(RequestCity requestCityReal, RequestCity requestCityFake) {

        ResponseCity responseCity = responseCityAdaptor.postForCityDetail(requestCityFake);

        log.info("POST for City: result = {}", responseCity);

        log.info("Return fake: {}, \ninstead of the real one: {}", requestCityFake, requestCityReal);
        return responseCity;
    }
}
