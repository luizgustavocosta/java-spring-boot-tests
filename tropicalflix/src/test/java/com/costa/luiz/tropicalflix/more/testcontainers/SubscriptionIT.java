package com.costa.luiz.tropicalflix.more.testcontainers;

import com.costa.luiz.tropicalflix.subscription.StartSubscriptionRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@DisplayName("Subscription IT")
@Disabled
class SubscriptionIT {

    @Container
    static final GenericContainer<?> financialService = new GenericContainer<>(
            DockerImageName.parse("16bits/financial-service:0.0.1"))
            .withExposedPorts(8081)
            .withNetworkAliases("tropicalFlix")
            .withNetwork(Network.SHARED)
            .waitingFor(Wait.forLogMessage(".*Started .*", 1));

    @Container
    static final GenericContainer<?> tropicalFlix = new GenericContainer<>(
            DockerImageName.parse("16bits/tropicalflix:0.0.1"))
            .withExposedPorts(8080)
            .dependsOn(financialService)
            .withNetwork(financialService.getNetwork())
            .waitingFor(Wait.forLogMessage(".*Started .*", 1));

    static {
        Startables.deepStart(financialService, tropicalFlix);
    }

    @DynamicPropertySource
    static void tropicalFlixProperties(DynamicPropertyRegistry registry) {
        registry.add("service.financial.url", financialService::getFirstMappedPort);
    }

    @Test
    void sendAPayment() {
        assertTrue(financialService.isRunning());
        RestClient restClient = RestClient.builder()
                .baseUrl(String.format("http://localhost:%d", financialService.getFirstMappedPort()))
                .build();
        ResponseEntity<PaymentResponse> response = restClient
                .post()
                .uri("/api/v1/payments")
                .body(new PaymentRequest(null, null, "HASH"))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
        assertNotNull(response.getBody().message());
    }

    @Test
    void subscribe() {
        RestClient restClient = RestClient.builder()
                .baseUrl(String.format("http://localhost:%d", tropicalFlix.getFirstMappedPort()))
                .build();

        ResponseEntity<String> responseEntity = restClient.post().uri("/api/v1/subscriptions")
                .body(new StartSubscriptionRequest("luiz@email.com", null, null, "Brazil"))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
        assertNotNull(responseEntity);
    }

}
