package ru.afilonov.grpcservice.configs;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class AmpqConfig {
    public static final String QUEUE_NAME = "reports";
}
