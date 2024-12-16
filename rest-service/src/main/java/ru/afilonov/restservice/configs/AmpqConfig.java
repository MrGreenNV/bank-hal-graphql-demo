package ru.afilonov.restservice.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmpqConfig {

    public static final String QUEUE_NAME = "reports";

    @Bean
    public Queue requestQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
