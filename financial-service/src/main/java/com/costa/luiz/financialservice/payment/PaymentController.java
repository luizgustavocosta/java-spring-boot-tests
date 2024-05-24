package com.costa.luiz.financialservice.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping
    ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest) {
        logger.info("New request to be processed {}", paymentRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body
                        (PaymentResponse.PaymentResponseBuilder.aPaymentResponse()
                                .withId(UUID.randomUUID().toString().substring(0, 18))
                                .withMessage("Your request has been registered")
                                .withStatus(
                                        PaymentStatus.values()
                                                [ThreadLocalRandom.current()
                                                .nextInt(0, PaymentStatus.values().length - 1)])
                .build());
    }
}
