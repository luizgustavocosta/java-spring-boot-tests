package com.costa.luiz.tropicalflix.subscription.external.financial;

public record PaymentResponse(String id, PaymentStatus status, String message) {

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
