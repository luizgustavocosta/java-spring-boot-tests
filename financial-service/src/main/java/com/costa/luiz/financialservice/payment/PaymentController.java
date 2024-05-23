package com.costa.luiz.financialservice.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @PostMapping
    ResponseEntity<PaymentResponse> payment(PaymentRequest paymentRequest) {
        return ResponseEntity.ok(PaymentResponse.PaymentResponseBuilder.aPaymentResponse()
                .withId(UUID.randomUUID().toString().substring(0, 18))
                .withMessage("Your request has been registered")
                .withStatus(PaymentStatus.values()
                        [ThreadLocalRandom.current().nextInt(0, PaymentStatus.values().length - 1)])
                .build());
    }
}
