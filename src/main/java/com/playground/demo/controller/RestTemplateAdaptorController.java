package com.playground.demo.controller;

import com.playground.demo.model.response.CityResponse;
import com.playground.demo.model.response.HttpBinGetResponse;
import com.playground.demo.model.request.CityRequest;
import com.playground.demo.model.test.TwoCityRequest;
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
    public CityResponse testRestTemplatePostMethod(@RequestBody CityRequest cityRequest){
        return restTemplateServiceWithAdaptor.testRestTemplatePostMethodCity(cityRequest);
    }

    @DeleteMapping("/cache/clear/city")
    public void clearResponseCityCache(@RequestBody CityRequest cityRequest){
        restTemplateServiceWithAdaptor.clearResponseCityCache(cityRequest);
    }

    @PutMapping("/cache/put/city")
    public CityResponse troubleMakingCityCache(@RequestBody TwoCityRequest citiesRequest){
        return restTemplateServiceWithAdaptor.troubleMaking(citiesRequest.getCityRequestReal(), citiesRequest.getCityRequestFake());
    }

}


