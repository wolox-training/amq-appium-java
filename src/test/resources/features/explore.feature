Feature: Favorites verification

  Scenario Outline: validate favorite
    Given I am in the Movie Swiper app
    And  I click on the "Login" button
    And  I authenticate the page
    And I enter the explore menu
    When I move the image of the movie to the "<direction>"
    Then The movie is going to "<library>"

    Examples:
      | direction | library     |
      | right     | MyWatchlist |
      | up        | MyFavorite  |
      | left      | none        |
