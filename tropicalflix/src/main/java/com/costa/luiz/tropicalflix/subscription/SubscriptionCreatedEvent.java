package com.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

class SubscriptionCreatedEvent extends ApplicationEvent { // Custom Event

    SubscriptionCreatedEvent(Object source, StartSubscription startSubscription) {
        super(source);
        Logger logger = LoggerFactory.getLogger(SubscriptionCreatedEvent.class);
        logger.info("Event has arrived [{}]", startSubscription);
    }
}
