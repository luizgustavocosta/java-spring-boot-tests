Feature: retrieve all movies via API
  Scenario: client makes API call to GET /api/v1/movies
    When the client calls /api/v1/movies
    Then the client receives status code of 200
    And the client receives the data