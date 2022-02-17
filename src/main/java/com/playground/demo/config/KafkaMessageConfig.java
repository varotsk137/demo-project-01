package com.playground.demo.config;

import com.playground.demo.model.request.GameDtoRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class KafkaMessageConfig {

    @Bean
//    @Qualifier("GameDtoRequestKafkaTemplate")
    public KafkaTemplate<String, GameDtoRequest> kafkaTemplateForGameDto() {
        return new KafkaTemplate<>(producerFactoryForGameDtoRequest());
    }

    @Primary
    @Bean
    public KafkaTemplate<String, String> kafkaTemplateString() {
        return new KafkaTemplate<>(producerFactoryString());
    }

    @Bean
    public ProducerFactory<String, GameDtoRequest> producerFactoryForGameDtoRequest(){
        return new DefaultKafkaProducerFactory<>(producerConfigForGameDtoRequest());
    }

    @Bean
    public ProducerFactory<String, String> producerFactoryString(){
        return new DefaultKafkaProducerFactory<>(producerConfigString());
    }

    @Bean
    public Map<String, Object> producerConfigForGameDtoRequest() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS,
                "gameDtoRequest:com.playground.demo.model.request.GameDtoRequest");
        return props;
    }

    @Bean
    public Map<String, Object> producerConfigString() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

}
