package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.subscription.external.financial.FinancialServiceClient;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        PaymentResponse paymentResponse = financialServiceClient.pay(new PaymentRequest("1", null, "42")).getBody();
        logger.info("{}", paymentResponse);
        subscriptionPublisher.publish(request);
    }
}
