package com.costa.luiz.tropicalflix.subscription;

record StartSubscriptionRequest(
        String email,
        String billingAmount,
        String billingPeriod,
        String country) {
}

