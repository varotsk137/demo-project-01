package com.playground.demo.adaptor;

import com.playground.demo.model.HttpBinGetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpBinAdaptor extends DemoAdaptor{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public HttpBinGetEntity getHttpBinEntityFromUrl(String url){
        return super.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, HttpBinGetEntity.class).getBody();
    }
    
}
