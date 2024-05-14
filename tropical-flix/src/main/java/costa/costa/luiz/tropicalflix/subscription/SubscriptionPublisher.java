package costa.costa.luiz.tropicalflix.subscription;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SubscriptionPublisher {

    private final ApplicationEventPublisher publisher;

    public SubscriptionPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(StartSubscriptionRequest subscription) {
        List<String> financial = List.of("Rede", "Stripe", "PayPal"); // Could be a database call
        String financialProcessor = "";
        switch (subscription.country()) {
            case "Brasil" -> financialProcessor = financial.get(0);
            case "USA" -> financialProcessor = financial.get(1);
            default -> financialProcessor = financial.get(2);
        }
        StartSubscription startSubscription = new StartSubscription(
                UUID.randomUUID().toString().substring(0, 10),
                subscription.email(), subscription.billingAmount(),
                subscription.billingPeriod(), subscription.country(), financialProcessor
        );
        publisher.publishEvent(new SubscriptionCreatedEvent(startSubscription));
//        publisher.publishEvent(new MovieSuggestEvent()); You can fire nth events
    }
}
