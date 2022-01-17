package com.playground.demo.adaptor;

import com.playground.demo.model.RequestCity;
import com.playground.demo.model.ResponseCity;
import com.playground.demo.util.ReadInputUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ResponseCityAdaptorTest extends ReadInputUtil {

    @InjectMocks
    ResponseCityAdaptor responseCityAdaptor;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        ReflectionTestUtils.setField(responseCityAdaptor, "requestUrl", "https://countriesnow.space");
        ReflectionTestUtils.setField(responseCityAdaptor, "requestPath", "/api/v0.1/countries/population/cities");
    }

    @SneakyThrows
    @Test
    void postForCityDetail() {

        ResponseCity responseCity = super.loadClassFromJson(ResponseCity.class, "/json/citySydneyTest.json");

        RequestCity requestCity = RequestCity.builder()
                .city("Sydney")
                .build();

        doReturn(ResponseEntity.ok(responseCity))
                .when(restTemplate)
                .exchange(anyString(), any() , any() , eq(ResponseCity.class) );

        ResponseCity actualCity = responseCityAdaptor.postForCityDetail(requestCity);

        assertEquals(responseCity, actualCity);


    }
}