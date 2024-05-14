package costa.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public class SubscriptionCreatedEvent extends ApplicationEvent { // Custom Event

    public SubscriptionCreatedEvent(Object source) {
        super(source);
        var event = (StartSubscription) source;
        Logger logger = LoggerFactory.getLogger(SubscriptionCreatedEvent.class);
        logger.info("Event received [{}]", event);
    }
}
