package ru.afilonov.restservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.afilonov.restservice.ampq.RabbitMQProducer;

@Service
public class AmpqService {

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public AmpqService(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public void sendMessage(final String request) {
        rabbitMQProducer.sendMessage(request);
    }
}
