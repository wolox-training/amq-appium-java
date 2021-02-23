Feature: Login verification

  Scenario Outline: validate login
    Given I am in the Movie Swiper app
    And  I click on the "Login" button
    When I write the "<user>" and "<password>"
    And I click the login button
    Then the system displays the "<msg>" message

    Examples:
      | user     | password | msg   |
      | invalido | 1234567  | error |
