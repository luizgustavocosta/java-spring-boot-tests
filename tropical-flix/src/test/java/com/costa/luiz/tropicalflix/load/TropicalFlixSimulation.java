package com.costa.luiz.tropicalflix.load;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class TropicalFlixSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder moviesScenario = scenario("Movies").exec(http("Movies").get("/api/v1/movies"));
    ScenarioBuilder actorsScenario = scenario("Actors").exec(http("Actors").get("/api/v1/actors"));
    ScenarioBuilder genresScenario = scenario("Genres")
            .feed(generateGenre())
            .exec(http("Genres")
                    .post("/api/v1/genres")
                    .header("Content-Type", "application/json")
                    .body(StringBody("{ \"name\": \"#{name}\" }"))
                    .check(status().is(HttpStatus.CREATED.value())));

    {
        setUp(
                moviesScenario.injectOpen(
                                rampUsersPerSec(1).to(300).during(Duration.ofSeconds(30)))
                        .protocols(httpProtocol),
                actorsScenario.injectOpen(
                                rampUsersPerSec(1).to(300).during(Duration.ofSeconds(30)))
                        .protocols(httpProtocol),
                genresScenario.injectOpen(
                                rampUsersPerSec(1).to(300).during(Duration.ofSeconds(30)))
                        .protocols(httpProtocol));
    }

    private static Iterator<Map<String, Object>> generateGenre() {
        Iterator<Map<String, Object>> iterator;
        iterator = Stream.generate(() -> {
                    Map<String, Object> stringObjectMap = new HashMap<>();
                    stringObjectMap.put("name", UUID.randomUUID().toString().substring(0, 20));
                    return stringObjectMap;
                })
                .iterator();
        return iterator;
    }
}
