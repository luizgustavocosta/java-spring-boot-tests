package com.costa.luiz.tropicalflix.subscription;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class SubscriptionPublisher {

    private final ApplicationEventPublisher publisher;

    SubscriptionPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    void publish(StartSubscriptionRequest subscription) {
        List<String> financial = List.of("Rede", "Stripe", "PayPal"); // Could be a database call
        String financialProcessor = "";
        switch (subscription.country()) {
            case "Brasil" -> financialProcessor = financial.get(0);
            case "USA" -> financialProcessor = financial.get(1);
            default -> financialProcessor = financial.get(2);
        }
        StartSubscription startSubscription = new StartSubscription(
                UUID.randomUUID().toString().substring(0, 36),
                subscription.email(), subscription.billingAmount(),
                subscription.billingPeriod(), subscription.country(), financialProcessor
        );
        publisher.publishEvent(new SubscriptionCreatedEvent(this, startSubscription));
        publisher.publishEvent(new CreateRecommendationEvent(this, new Recommendation()));
    }
}
