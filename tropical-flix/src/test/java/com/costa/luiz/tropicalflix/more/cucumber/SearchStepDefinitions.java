package com.costa.luiz.tropicalflix.more.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

public class SearchStepDefinitions {

    @Given("{actor} is researching things on the internet")
    public void researchingThings(Actor actor) {

    }

    @When("{actor} looks up {string}")
    public void searchesFor(Actor actor, String term) {

    }

    @Then("{actor} should see information about {string}")
    public void should_see_information_about(Actor actor, String term) {

    }
}
