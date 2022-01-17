package com.playground.demo.adaptor;

import com.playground.demo.model.HttpBinGetEntity;
import com.playground.demo.util.ReadInputUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HttpBinAdaptorTest extends ReadInputUtil {

    @InjectMocks
    HttpBinAdaptor httpBinAdaptor;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        ReflectionTestUtils.setField(httpBinAdaptor, "requestUrl", "https://httpbin.org");
        ReflectionTestUtils.setField(httpBinAdaptor, "requestPath", "/get");
    }

    @SneakyThrows
    @Test
    void getHttpBinEntityFromUrl() {

        HttpBinGetEntity httpBinGetEntity = super.loadClassFromJson(HttpBinGetEntity.class, "/json/httpBinTest.json");

        doReturn(ResponseEntity.ok(httpBinGetEntity))
                .when(restTemplate)
                .exchange(anyString(), any() , any() , eq(HttpBinGetEntity.class) );

        HttpBinGetEntity actualHttpBin = httpBinAdaptor.getHttpBinEntityFromUrl();

        assertNotNull(actualHttpBin);
        assertEquals(httpBinGetEntity, actualHttpBin);
        verify(restTemplate, times(1)).exchange(anyString(), any() , any() , eq(HttpBinGetEntity.class));

    }
}