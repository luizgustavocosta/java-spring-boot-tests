package costa.costa.luiz.tropicalflix.subscription;

record StartSubscriptionRequest(
        Long userId,
        String email,
        String billingAmount,
        String billingPeriod,
        String country) {
}

