package com.playground.demo.controller;

import com.playground.demo.model.request.GameDtoRequest;
import com.playground.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@RequestMapping(value = "/api/kafka")
public class KafkaController {

    @Autowired
    private ProducerService producerService;

    @GetMapping(value = "/send")
    public void sendMessage(@RequestParam String message){
        this.producerService.produce(message);
    }

    @PostMapping(value = "/send")
    public void sendBody(@RequestBody GameDtoRequest gameDtoRequest){
        this.producerService.sendBody(gameDtoRequest);
    }

    @PostMapping(value = "/send/file")
    public void sendFile(InputStream inputStream){
        this.producerService.sendFile(inputStream);
    }

}
