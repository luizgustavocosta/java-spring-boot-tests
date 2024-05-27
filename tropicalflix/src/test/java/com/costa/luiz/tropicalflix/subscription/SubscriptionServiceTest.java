package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.subscription.external.financial.FinancialServiceClient;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentResponse;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubscriptionServiceTest {

    SubscriptionService subscriptionService;

    @Mock
    private SubscriptionPublisher subscriptionPublisher;

    @Mock
    private FinancialServiceClient financialServiceClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriptionService = new SubscriptionService(subscriptionPublisher, financialServiceClient);
    }

    @Test
    void triggerSubscriptionWorkflow() {
        var request = new StartSubscriptionRequest("email@email.com", "42.99", "90", "Brazil");
        ResponseEntity<PaymentResponse> response = ResponseEntity.accepted()
                .body(new PaymentResponse("9", PaymentStatus.SUCCEDED, "Random message"));
        when(financialServiceClient.pay(any(PaymentRequest.class))).thenReturn(response);

        subscriptionService.triggerSubscriptionWorkflow(request);

        var captor = ArgumentCaptor.forClass(StartSubscriptionRequest.class);
        verify(subscriptionPublisher).publish(captor.capture());

        StartSubscriptionRequest subscriptionRequest = captor.getValue();
        assertEquals(request, subscriptionRequest);
    }
}