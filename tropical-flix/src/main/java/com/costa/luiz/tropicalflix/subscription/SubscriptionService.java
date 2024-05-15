package com.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class SubscriptionService {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionPublisher subscriptionPublisher;

    SubscriptionService(SubscriptionPublisher subscriptionPublisher) {
        this.subscriptionPublisher = subscriptionPublisher;
    }

    void triggerSubscriptionWorkflow(StartSubscriptionRequest request) {
        logger.info("{}", Thread.currentThread());
        subscriptionPublisher.publish(request);
    }
}
