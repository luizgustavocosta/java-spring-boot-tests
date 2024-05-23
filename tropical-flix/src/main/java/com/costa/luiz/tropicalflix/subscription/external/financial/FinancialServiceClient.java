package com.costa.luiz.tropicalflix.subscription.external.financial;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FinancialServiceClient {

    private final RestClient restClient;

    private FinancialProperties financialProperties;

    public FinancialServiceClient(RestClient.Builder builder, FinancialProperties financialProperties) {
        this.restClient = builder
                .baseUrl(financialProperties.getUrl())
                .requestFactory(new JdkClientHttpRequestFactory())
                .build();
    }

    public ResponseEntity<PaymentResponse> pay(PaymentRequest paymentRequest) {
        return restClient.post()
                .uri("/api/v1/payment")
                .body(paymentRequest)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
    }
}
