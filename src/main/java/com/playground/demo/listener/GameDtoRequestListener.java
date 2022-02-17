package com.playground.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class GameDtoRequestListener {

    @KafkaListener(topics = "random.discount")
    public void gameDtoRandomDiscount(String message){
        log.info("Listener on random.discount: {{}}", message);
    }

}
