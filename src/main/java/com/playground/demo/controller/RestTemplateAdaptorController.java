package com.playground.demo.controller;

import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import com.playground.demo.model.test.TwoRequestCity;
import com.playground.demo.service.RestTemplateService;
import com.playground.demo.service.RestTemplateServiceWithAdaptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestTemplateAdaptorController {

    @Autowired
    RestTemplateServiceWithAdaptor restTemplateServiceWithAdaptor;

    @GetMapping("/adaptor/get")
    public HttpBinGetEntity testRestTemplateGetMethod(){
        return restTemplateServiceWithAdaptor.testRestTemplateGetMethod();
    }

    @PostMapping("/adaptor/post")
    public ResponseCity testRestTemplatePostMethod(@RequestBody RequestCity requestCity){
        return restTemplateServiceWithAdaptor.testRestTemplatePostMethodCity(requestCity);
    }

    @DeleteMapping("/cache/clear/city")
    public void clearResponseCityCache(@RequestBody RequestCity requestCity){
        restTemplateServiceWithAdaptor.clearResponseCityCache(requestCity);
    }

    @PutMapping("/cache/put/city")
    public ResponseCity troubleMakingCityCache(@RequestBody TwoRequestCity requestCities){
        return restTemplateServiceWithAdaptor.troubleMaking(requestCities.getRequestCityReal(), requestCities.getRequestCityFake());
    }

}


