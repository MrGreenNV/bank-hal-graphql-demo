package ru.afilonov.grpcservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import ru.afilonov.report.Banking;
import ru.afilonov.report.BankingServiceGrpc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.*;
import java.util.Base64;

@GrpcService
public class BankingServiceServer extends BankingServiceGrpc.BankingServiceImplBase {
    private static final String USER_HOME_DIR = System.getProperty("user.home");

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void report(Banking.ReportRequest request, StreamObserver<Banking.ReportResponse> responseObserver) {
        final String pathToReport = this.getReport(request.getPersonId());

        final Banking.ReportResponse reportResponse = Banking.ReportResponse.newBuilder()
                .setReportUrl(pathToReport)
                .build();

        responseObserver.onNext(reportResponse);
        responseObserver.onCompleted();
    }

    public String getReport(final long personId) {
        final String query = "select p.name, a.number, a.balance, c.number " +
                "from people p " +
                "join accounts a on p.id = a.person_id " +
                "join cards c on a.id = c.account_id " +
                "where p.id = ?";
        final Path pathToReport;
        try (final Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             final PreparedStatement prSt = con.prepareStatement(query)) {

            prSt.setLong(1, personId);

            final ResultSet rs = prSt.executeQuery();
            final String report = prepareReport(rs);
            pathToReport = saveToFile(report);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pathToReport.toString();
    }

    private Path saveToFile(final String content) {
        final String filename = "report.txt";
        final File file = new File(USER_HOME_DIR, filename);
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Path.of(USER_HOME_DIR, filename);
    }

    private String prepareReport(final ResultSet rs) throws SQLException {
        final StringBuilder report = new StringBuilder();
        while (rs.next()) {
            final String pName = rs.getString(1);
            final String aNum = rs.getString(2);
            final double aBalance = rs.getDouble(3);
            final String cNum = rs.getString(4);

            report.append("Клиент: ").append(pName).append("\n");
            report.append("Номер счёта: ").append(aNum).append("\n");
            report.append("Баланс: ").append(aBalance).append("\n");
            report.append("Номер карты: ").append(cNum).append("\n");
        }
        return new String(report.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}
