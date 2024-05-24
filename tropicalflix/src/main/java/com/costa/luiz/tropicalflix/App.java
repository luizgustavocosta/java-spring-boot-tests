package com.costa.luiz.tropicalflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @Bean
//    public FinancialServiceClient doStuff() {
//        RestClient client = RestClient.builder().baseUrl("http://localhost:8081").build();
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
//        return factory.createClient(FinancialServiceClient.class);
//    }
}
