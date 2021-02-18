Feature: Browse verification

  Scenario Outline: validate Browse
    Given I am in the Movie Swiper app
    And  I click on the "Login" button
    And  I authenticate the page
    When I am looking for a movie "<movie>"
    And I select one from the list of results
    Then the system displays the information of the selected movie


    Examples:
      | movie          |
      | Wonder Woman   |
      | son como niños |
