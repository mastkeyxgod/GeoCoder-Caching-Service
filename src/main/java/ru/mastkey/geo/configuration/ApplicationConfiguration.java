package ru.mastkey.geo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Value("${redis.server}")
    private String SERVER;
    @Value("${redis.port}")
    public int PORT;

    @Bean
    public Jedis jedis() {
        return new Jedis(SERVER, PORT);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
