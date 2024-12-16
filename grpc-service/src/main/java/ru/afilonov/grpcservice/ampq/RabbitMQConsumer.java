package ru.afilonov.grpcservice.ampq;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.afilonov.grpcservice.configs.AmpqConfig;
import ru.afilonov.grpcservice.grpc.BankingServiceServer;

@Service
public class RabbitMQConsumer {
    private final BankingServiceServer bankingServiceServer;
    private final InternalLogger logger = Log4J2LoggerFactory.getInstance(this.getClass());

    public RabbitMQConsumer(BankingServiceServer bankingServiceServer) {
        this.bankingServiceServer = bankingServiceServer;
    }

    @RabbitListener(queues = AmpqConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        final String pathToReport = bankingServiceServer.getReport(Long.parseLong(message));
        logger.info("Отчёт доступен по пути: " + pathToReport);
    }
}
