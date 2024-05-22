package com.costa.luiz.tropicalflix;

import com.costa.luiz.tropicalflix.subscription.FinancialServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
@EnableCaching
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public FinancialServiceClient doStuff() {
        RestClient client = RestClient.builder().baseUrl("http://localhost:8081").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
        return factory.createClient(FinancialServiceClient.class);
    }
}
