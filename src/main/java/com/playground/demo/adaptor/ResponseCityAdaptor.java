package com.playground.demo.adaptor;

import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResponseCityAdaptor extends DemoAdaptor{

    @Value("${adaptor.url.city}")
    private String requestUrl;

    @Value("${adaptor.path.city.postByCityName}")
    private String requestPath;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }


    public ResponseCity postForCityDetail(RequestCity requestCity){

        String url = super.concat(requestUrl, requestPath);

        HttpEntity requestCityEntity = new HttpEntity(requestCity);
        ResponseCity response;

        try {
            response = super.exchange(url, HttpMethod.POST, requestCityEntity, ResponseCity.class).getBody();
        } catch (HttpClientErrorException httpEx) {
            response = ResponseCity.builder()
                    .error(true)
                    .msg(httpEx.getMessage())
                    .build();
        }

        return response;
    }
    
}
