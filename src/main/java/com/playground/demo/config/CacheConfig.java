package com.playground.demo.config;

import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class CacheConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("#{${redis.map.ttl}}")
    private Map<String, Duration> ttlMap;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }


    // Default Cache Config
    @Primary
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5))
                .disableCachingNullValues();
    }

    // Named Cache Config
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration("httpBin",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration("responseCity",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2)));
    }

    @Bean
    public RedisCacheManager customCacheManager() {

        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();

        if (!ttlMap.isEmpty()) {
            for (Map.Entry<String, Duration> nameAndTtl : ttlMap.entrySet()) {
                cacheConfigurationMap.put(nameAndTtl.getKey(), RedisCacheConfiguration.defaultCacheConfig().entryTtl(nameAndTtl.getValue()));
            }
        }

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .withInitialCacheConfigurations(cacheConfigurationMap)
                .build();

    }

}
