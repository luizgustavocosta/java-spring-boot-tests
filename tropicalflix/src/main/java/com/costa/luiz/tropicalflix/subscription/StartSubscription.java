package com.costa.luiz.tropicalflix.subscription;

record StartSubscription(String uuid, String email, String billingAmount, String billingPeriod, String country,
                         String financialProcessor) {
}
