Feature: Browse verification

  Scenario Outline: validate Browse
    Given I am in the Movie Swiper app
    And  I click on the "Login" button
    And I write the "<user>" and "<password>"
    And I click the login button
    When I am looking for a movie "<movie>"
    And I select one from the list of results
    Then the system displays the information of the selected movie


    Examples:
      | user        | password | movie          |
      | anaquintero | anamaria | Wonder Woman   |
      #| anaquintero | anamaria | son como niños |
