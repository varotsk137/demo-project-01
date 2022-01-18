package com.playground.demo.controller;

import com.playground.demo.model.CityResponse;
import com.playground.demo.model.HttpBinGetResponse;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.test.TwoRequestCity;
import com.playground.demo.service.RestTemplateServiceWithAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestTemplateAdaptorController {

    @Autowired
    RestTemplateServiceWithAdaptor restTemplateServiceWithAdaptor;

    @GetMapping("/adaptor/get")
    public HttpBinGetResponse testRestTemplateGetMethod(){
        return restTemplateServiceWithAdaptor.testRestTemplateGetMethod();
    }

    @PostMapping("/adaptor/post")
    public CityResponse testRestTemplatePostMethod(@RequestBody RequestCity requestCity){
        return restTemplateServiceWithAdaptor.testRestTemplatePostMethodCity(requestCity);
    }

    @DeleteMapping("/cache/clear/city")
    public void clearResponseCityCache(@RequestBody RequestCity requestCity){
        restTemplateServiceWithAdaptor.clearResponseCityCache(requestCity);
    }

    @PutMapping("/cache/put/city")
    public CityResponse troubleMakingCityCache(@RequestBody TwoRequestCity requestCities){
        return restTemplateServiceWithAdaptor.troubleMaking(requestCities.getRequestCityReal(), requestCities.getRequestCityFake());
    }

}


