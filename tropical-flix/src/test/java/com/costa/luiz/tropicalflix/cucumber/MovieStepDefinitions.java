package com.costa.luiz.tropicalflix.cucumber;

import com.costa.luiz.tropicalflix.movie.MovieDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(MovieStepDefinitions.class);

    @Autowired
    private TropicalFlixHttpClient bagHttpClient;

//    @When("^I put (\\d+) (\\w+) in the bag$")
    @When("^the client calls /api/v1/movies$")
    public void i_put_something_in_the_bag() {
//        IntStream.range(0, quantity)
//                .peek(n -> log.info("Putting a {} in the bag, number {}", something, quantity))
//                .map(ignore -> bagHttpClient.put(something))
//                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_bag_should_contain_only_something(final int code) {
//        assertNumberOfTimes(quantity, something, true);
    }

    @Then("^the client receives the data$")
    public void the_bag_should_contain_something() {
    }

    private void assertNumberOfTimes(final int quantity, final String something, final boolean onlyThat) {
        ResponseEntity responseEntity = bagHttpClient.getContents();
        List<MovieDTO> body = (List<MovieDTO>) responseEntity.getBody();
        log.info("Expecting {} times {}. The bag contains {}", quantity, something, body);
        final int timesInList = Collections.frequency(body, something);
        assertThat(timesInList).isEqualTo(quantity);
        if (onlyThat) {
            assertThat(timesInList).isEqualTo(body.size());
        }
    }

}

