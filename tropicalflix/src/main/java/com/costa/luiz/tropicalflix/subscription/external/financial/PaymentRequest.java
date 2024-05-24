package com.costa.luiz.tropicalflix.subscription.external.financial;

public record PaymentRequest(String id, BillingDetails billingDetails, String cardHash) {
}

