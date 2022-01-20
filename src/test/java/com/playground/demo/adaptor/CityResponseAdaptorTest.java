package com.playground.demo.adaptor;

import com.playground.demo.model.request.CityRequest;
import com.playground.demo.model.response.CityResponse;
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
class CityResponseAdaptorTest extends ReadInputUtil {

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

        CityResponse cityResponse = super.loadClassFromJson(CityResponse.class, "/json/citySydneyTest.json");

        CityRequest cityRequest = CityRequest.builder()
                .city("Sydney")
                .build();

        doReturn(ResponseEntity.ok(cityResponse))
                .when(restTemplate)
                .exchange(anyString(), any() , any() , eq(CityResponse.class) );

        CityResponse actualCity = responseCityAdaptor.postForCityDetail(cityRequest);

        assertEquals(cityResponse, actualCity);

    }
}