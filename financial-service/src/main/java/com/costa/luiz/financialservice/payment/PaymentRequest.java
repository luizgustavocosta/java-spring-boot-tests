package com.costa.luiz.financialservice.payment;

record PaymentRequest(String id, BillingDetails billingDetails, String cardHash) {
}

