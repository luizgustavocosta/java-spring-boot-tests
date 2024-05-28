package com.costa.luiz.tropicalflix.more.gatlin;

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
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class TropicalFlixSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder moviesScenario = scenario("Movies").exec(http("Movies").get("/api/v1/movies"));
    ScenarioBuilder actorsScenario = scenario("Actors").exec(http("Actors").get("/api/v1/actors"));
    ScenarioBuilder genresScenario = scenario("Genres")
            .feed(feeder)
            .exec(http("Genres")
                    .post("/api/v1/genres")
                    .header("Content-Type", "application/json")
                    .body(StringBody("#{payload}"))
                    .check(status().is(HttpStatus.CREATED.value())));

    {
        int seconds = 60;
        int usersGoal = 180;
        int rampUsersPerSecond = 5;
        int warmUpUsersPerSecond = 2;
        int usersPerSecond = 5;
        int durationOf5 = 5;
        int durationOf10 = 10;

        setUp(
                moviesScenario.injectOpen(
                                constantUsersPerSec(warmUpUsersPerSecond).during(Duration.ofSeconds(durationOf5)),
                                constantUsersPerSec(usersPerSecond).during(Duration.ofSeconds(durationOf10)).randomized(),
                                rampUsersPerSec(rampUsersPerSecond).to(usersGoal).during(Duration.ofSeconds(seconds))) 
                        .protocols(httpProtocol),
                actorsScenario.injectOpen(
                                constantUsersPerSec(warmUpUsersPerSecond).during(Duration.ofSeconds(durationOf5)),
                                constantUsersPerSec(usersPerSecond).during(Duration.ofSeconds(durationOf10)).randomized(),
                                rampUsersPerSec(rampUsersPerSecond).to(usersGoal).during(Duration.ofSeconds(seconds))) 
                        .protocols(httpProtocol),
                genresScenario.injectOpen(
                                constantUsersPerSec(warmUpUsersPerSecond).during(Duration.ofSeconds(durationOf5)),
                                constantUsersPerSec(usersPerSecond).during(Duration.ofSeconds(durationOf10)).randomized(),
                                rampUsersPerSec(rampUsersPerSecond).to(usersGoal).during(Duration.ofSeconds(seconds))) 
                        .protocols(httpProtocol));
    }
    private static final Iterator<Map<String, Object>> feeder =
            Stream.generate(() -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("payload",
                                """
                                        {"name":"%s"}
                                        """.formatted(UUID.randomUUID().toString().substring(0, 18))
                        );
                        return map;
                    })
                    .iterator();
}
