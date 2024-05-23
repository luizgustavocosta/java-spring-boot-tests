package com.costa.luiz.financialservice.payment;

record PaymentResponse(String id, PaymentStatus status, String message) {


    public static final class PaymentResponseBuilder {
        private String id;
        private PaymentStatus status;
        private String message;

        private PaymentResponseBuilder() {
        }

        public static PaymentResponseBuilder aPaymentResponse() {
            return new PaymentResponseBuilder();
        }

        public PaymentResponseBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public PaymentResponseBuilder withStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public PaymentResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(id, status, message);
        }
    }
}
