package com.costa.luiz.tropicalflix.subscription;

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
        subscriptionPublisher.publish(request);
        financialServiceClient.pay();//FIXME
    }
}
