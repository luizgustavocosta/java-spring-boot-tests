package com.costa.luiz.tropicalflix.subscription.external.financial;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableWireMock({
        @ConfigureWireMock(name = "financial", property = "service.financial.url")
})
@ExtendWith(OutputCaptureExtension.class)
class FinancialServiceClientTest {

    @InjectWireMock("financial")
    private WireMockServer wiremock;


    @Value("${service.financial.url}")
    private String wiremockUrl;

    @Autowired
    private FinancialServiceClient financialServiceClient;

    @Test
    @DisplayName("Call the financial service to perform the payment")
    void callTheFinancialService(CapturedOutput capturedOutput) throws IOException, InterruptedException {
        PaymentRequest paymentRequest = new PaymentRequest("11", null, "AAA");
        wiremock.stubFor(post("/api/v1/payments")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.ACCEPTED.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                   "id": "6e758a17-674f-4a90",
                                   "status": "SUCCEDED",
                                   "message": "Your request has been registered"
                                 }
                                """)
                )
        );


        ResponseEntity<PaymentResponse> response = financialServiceClient.pay(paymentRequest);

        //Bad example of use of different libraries
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        org.hamcrest.MatcherAssert.assertThat(response.getBody().message(),
                equalTo("Your request has been registered"));
        assertThat(capturedOutput.getAll())
                .as("Must contain debug logging for WireMock")
                .contains("Matched response definition:");
    }
}