package com.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

class SubscriptionCreatedEvent extends ApplicationEvent { // Custom Event

    private final StartSubscription startSubscription;

    SubscriptionCreatedEvent(Object source, StartSubscription startSubscription) {
        super(source);
        this.startSubscription = startSubscription;
        Logger logger = LoggerFactory.getLogger(SubscriptionCreatedEvent.class);
        logger.info("Event has arrived [{}]", this.startSubscription);
    }

    StartSubscription getStartSubscription() {
        return startSubscription;
    }
}
