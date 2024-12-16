package ru.afilonov.restservice.services;

import ru.afilonov.report.BankingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.afilonov.report.Banking;

@Service
public class GrpcService {

    @GrpcClient("BankingService")
    private BankingServiceGrpc.BankingServiceBlockingStub blockingStub;

    public GrpcService(BankingServiceGrpc.BankingServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    public GrpcService() {}

    public String getPathOfReport(final long personId) {
        final Banking.ReportRequest reportRequest = Banking.ReportRequest.newBuilder()
                .setPersonId(personId)
                .build();

        final Banking.ReportResponse reportResponse = blockingStub.report(reportRequest);
        return reportResponse.getReportUrl();
    }
}
