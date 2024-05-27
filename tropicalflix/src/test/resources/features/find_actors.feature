Feature: Actors API

  @green
  Scenario: Find all actors
    When a GET request is made to "/api/v1/actors"
    Then list of actors is returned

  @red
  Scenario: Find a specific actor
    When a GET request is made to "/api/v1/actors/42"
    Then the specific actor is returned
