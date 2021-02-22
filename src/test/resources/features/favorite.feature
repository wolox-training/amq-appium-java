Feature: Favorites verification

  Scenario Outline: validate favorite
    Given I am in the Movie Swiper app
    And  I click on the "Login" button
    And  I authenticate the page
    When I select a movie from the "<topic>"
    And I click on favorite
    Then The movie is added to my favorites


    Examples:
      | topic           |
      | Trending Daily  |
      | Trending Weekly |
      | Popular         |
      | Top Rated       |
