package com.playground.demo.controller;

import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import com.playground.demo.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestTemplateController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping("/get/test/1")
    public HttpBinGetEntity testRestTemplateGetMethod(){
        return restTemplateService.testRestTemplateGetMethod();
    }

    @GetMapping("/get/test/2")
    public HttpBinGetEntity testRestTemplateGetMethod2(){
        return restTemplateService.testRestTemplateGetMethod2();
    }

    @PostMapping("/post/city")
    public ResponseCity testRestTemplatePostMethod(@RequestBody RequestCity requestCity){
        return restTemplateService.testRestTemplatePostMethodCity(requestCity);
    }

    @PostMapping("/post/city2")
    public ResponseCity testRestTemplatePostMethod2(@RequestBody RequestCity requestCity){
        return restTemplateService.testRestTemplatePostMethodCity2( requestCity);
    }

}
