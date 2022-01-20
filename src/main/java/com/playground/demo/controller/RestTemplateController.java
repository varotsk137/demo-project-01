package com.playground.demo.controller;

import com.playground.demo.model.response.CityResponse;
import com.playground.demo.model.response.HttpBinGetResponse;
import com.playground.demo.model.request.CityRequest;
import com.playground.demo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestTemplateController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping("/get/test/1")
    public HttpBinGetResponse testRestTemplateGetMethod(){
        return restTemplateService.testRestTemplateGetMethod();
    }

    @GetMapping("/get/test/2")
    public HttpBinGetResponse testRestTemplateGetMethod2(){
        return restTemplateService.testRestTemplateGetMethod2();
    }

    @PostMapping("/post/city")
    public CityResponse testRestTemplatePostMethod(@RequestBody CityRequest cityRequest){
        return restTemplateService.testRestTemplatePostMethodCity(cityRequest);
    }

    @PostMapping("/post/city2")
    public CityResponse testRestTemplatePostMethod2(@RequestBody CityRequest cityRequest){
        return restTemplateService.testRestTemplatePostMethodCity2(cityRequest);
    }

}
