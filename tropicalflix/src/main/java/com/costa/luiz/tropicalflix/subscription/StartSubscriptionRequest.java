package com.costa.luiz.tropicalflix.subscription;

public record StartSubscriptionRequest(
        String email,
        String billingAmount,
        String billingPeriod,
        String country) {
}

