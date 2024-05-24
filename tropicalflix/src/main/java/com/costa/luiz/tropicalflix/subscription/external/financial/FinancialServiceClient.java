package com.costa.luiz.tropicalflix.subscription.external.financial;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Service
public class FinancialServiceClient {

    private final RestClient restClient;

    private final FinancialProperties financialProperties;

    public FinancialServiceClient(RestClient.Builder builder, FinancialProperties financialProperties) {
        this.financialProperties = financialProperties;
        var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        var requestFactory = new JdkClientHttpRequestFactory(client);
        this.restClient = builder
                .baseUrl(financialProperties.getUrl())
                .requestFactory(requestFactory)
                .build();
    }

    public ResponseEntity<PaymentResponse> pay(PaymentRequest paymentRequest) {
        return restClient.post()
                .uri(financialProperties.getPaymentPath())
                .body(paymentRequest)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
    }
}
