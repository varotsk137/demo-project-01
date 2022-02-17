package com.playground.demo.service;

import com.playground.demo.model.request.GameDtoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
//    @Qualifier("GameDtoRequestKafkaTemplate")
    private KafkaTemplate<String, GameDtoRequest> kafkaTemplateTest;

    @Autowired
    private KafkaTemplate<String, InputStream> kafkaTemplateFileTest;

    public void produce(String message){
        String topic = "receiveMessage";
        log.info("Produce Topic: {}, Message: {}", topic, message);
        int i = 0;
        while(i < 10){
        kafkaTemplate.send(topic, String.format("%d: %s", ++i, message));
        kafkaTemplate.send(topic, null, String.format("%d: %s", i, message));
        }
    }

//    public void produce(String message){
//        String topic = "demokafka";
//        log.info("Produce Topic: {}, Message: {}", topic, message);
//        kafkaTemplate.send(topic, message);
//    }

    public void sendBody(GameDtoRequest gameDtoRequest) {
        String topic = "pub.model.gameDtoRequest";
        log.info("Produce Topic: {}, Body: {}", topic, gameDtoRequest);
        kafkaTemplateTest.send(topic, gameDtoRequest);
    }

    public void sendFile(InputStream inputStream) {
        String topic = "demo.sendFile";
        log.info("Product Topic: {}, Body: {}", topic, inputStream);
        kafkaTemplateFileTest.send(topic, inputStream);
    }
}
