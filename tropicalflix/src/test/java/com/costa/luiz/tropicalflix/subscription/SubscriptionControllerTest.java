package com.costa.luiz.tropicalflix.subscription;

import com.google.gson.Gson;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

class SubscriptionControllerTest {

    @Mock
    SubscriptionService subscriptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssuredMockMvc.standaloneSetup(new SubscriptionController(subscriptionService));
    }

    @Test
    void findAll() {
        given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/subscriptions")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(2))
                .body("get(0)", equalTo("Luiz"));
    }

    @Test
    void subscribe() {
        StartSubscriptionRequest request = new StartSubscriptionRequest("luiz@mail.com",
                "29.90", "90 days", "Spain");
        Gson gson = new Gson();

        given().auth().none()
                .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .body(gson.toJson(request))
                .when()
                .post("/api/v1/subscriptions")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(equalTo("Welcome to Tropicalflix"));
    }
}