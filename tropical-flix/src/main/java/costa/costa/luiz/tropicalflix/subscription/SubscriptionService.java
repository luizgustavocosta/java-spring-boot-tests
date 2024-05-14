package costa.costa.luiz.tropicalflix.subscription;

import org.springframework.stereotype.Service;

@Service
class SubscriptionService {

    private final SubscriptionPublisher subscriptionPublisher;

    SubscriptionService(SubscriptionPublisher subscriptionPublisher) {
        this.subscriptionPublisher = subscriptionPublisher;
    }

    public void triggerSubscriptionWorkflow(StartSubscriptionRequest request) {
        subscriptionPublisher.publish(request);
    }
}
