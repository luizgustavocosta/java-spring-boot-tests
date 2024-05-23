package com.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
class SubscriptionAsyncListener {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionAsyncListener.class);

    @Async
    @EventListener
    public void handle(SubscriptionCreatedEvent event) {
        logger.info("{}", Thread.currentThread());
        if (event.getSource() instanceof StartSubscription startSubscription) {
            logger.info("Time to notify the user via e-mail [{}]", startSubscription.email());
        }
    }
}
