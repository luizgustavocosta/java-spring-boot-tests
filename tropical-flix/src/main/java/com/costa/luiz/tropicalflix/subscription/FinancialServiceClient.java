package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentRequest;
import com.costa.luiz.tropicalflix.subscription.external.financial.PaymentStatus;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface FinancialServiceClient {

    @GetExchange("http://localhost:8081/api/v1/payments?{client-id}")
    List<String> payments();

    @PostExchange("payments")
    PaymentStatus pay(PaymentRequest paymentRequest);

}
