package com.playground.demo.adaptor;

import com.playground.demo.model.response.HttpBinGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpBinAdaptor extends DemoAdaptor{

    @Value("${adaptor.url.httpbin}")
    private String requestUrl;

    @Value("${adaptor.path.httpbin.get}")
    private String requestPath;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public HttpBinGetResponse getHttpBinEntityFromUrl(){

        String url = super.concat(requestUrl, requestPath);

        return super.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, HttpBinGetResponse.class).getBody();
    }
    
}
