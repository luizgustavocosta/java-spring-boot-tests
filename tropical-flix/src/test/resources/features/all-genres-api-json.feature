Feature: Retrieve all genres via API
  Scenario: client makes API call to GET /api/v1/genres
    When calls /api/v1/genres
    Then receives status code of 200
    And receives the data