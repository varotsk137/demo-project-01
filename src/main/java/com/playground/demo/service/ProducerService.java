package com.playground.demo.service;

import com.playground.demo.model.request.GameDtoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("GameDtoRequestKafkaTemplate")
    private KafkaTemplate<String, GameDtoRequest> kafkaTemplateTest;

    public void produce(String message){
        String topic = "demokafka";
        log.info("Produce Topic: {}, Message: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }

    public void sendBody(GameDtoRequest gameDtoRequest) {
        String topic = "pub.model.gameDtoRequest";
        log.info("Produce Topic: {}, Body: {}", topic, gameDtoRequest);
        kafkaTemplateTest.send(topic, gameDtoRequest);
    }

}
