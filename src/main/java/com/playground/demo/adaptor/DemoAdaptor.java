package com.playground.demo.adaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public abstract class DemoAdaptor {

    protected abstract RestTemplate getRestTemplate();

    //Exchange
    public <T> ResponseEntity<T> exchange(String url, HttpMethod httpMethod, HttpEntity httpEntity, Class<T> responseClass) {
        try {
            return getRestTemplate().exchange(url, httpMethod, httpEntity, responseClass);
        } catch (HttpClientErrorException httpEx) {
            log.error("HttpClientErrorException: {}", httpEx.getMessage() );
            throw httpEx;
//            return null;
        }
    }

    //Concatenate URL
    public String concat(String url, String path){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url)
                .path(path)
                .build();
        return uriComponents.toUriString();
    }


}
