package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.subscription.external.financial.BillingDetails;
import com.costa.luiz.tropicalflix.subscription.external.financial.FinancialServiceClient;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class SubscriptionService {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionPublisher subscriptionPublisher;
    private final FinancialServiceClient financialServiceClient;

    SubscriptionService(SubscriptionPublisher subscriptionPublisher, FinancialServiceClient financialServiceClient) {
        this.subscriptionPublisher = subscriptionPublisher;
        this.financialServiceClient = financialServiceClient;
    }

    void triggerSubscriptionWorkflow(StartSubscriptionRequest request) {
        logger.info("{}", Thread.currentThread());
        PaymentResponse paymentResponse = financialServiceClient
                .pay(new PaymentRequest(UUID.randomUUID().toString().substring(0, 36),
                        new BillingDetails("Spooner Street", "mail@mail.com", "Peter", "555-555"),
                        UUID.randomUUID().toString().substring(0, 10)))
                .getBody();
        logger.info("{}", paymentResponse);
        subscriptionPublisher.publish(request);
    }
}
