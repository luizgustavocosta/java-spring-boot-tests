package com.costa.luiz.tropicalflix.subscription;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok().body(Collections.emptyList());
    }

    @PostMapping
    ResponseEntity subscribe(StartSubscriptionRequest request) {
        subscriptionService.triggerSubscriptionWorkflow(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
