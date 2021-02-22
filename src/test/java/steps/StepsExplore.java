package steps;

import dtos.MovieData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.ExplorePage;
import pages.components.MenuBar;
import utils.DataManager;

public class StepsExplore {

    ExplorePage explorePage = new ExplorePage();


    @And("I enter the explore menu")
    public void iEnterTheExploreMenu() {
        MenuBar menuBar = new MenuBar();
        menuBar.clicExploreMenu();
    }

    @When("I move the image of the movie to the {string}")
    public void iMoveTheImageOfTheMovieToThe(String direction) {
        explorePage.clickImageMovie();

        MovieData movieData = DataManager.getMovieInformation();
        movieData.setTitleMovie(explorePage.getTitleMovie());
        movieData.setScoreMovie(explorePage.getScoreMovie());
        movieData.setYearMovie(explorePage.getYearMovie());

        switch (direction) {
            case "right":
                explorePage.saveMovie();
                break;
            case "up":
                explorePage.likeMovie();
                break;
            case "left":
            default:
                explorePage.skipMovie();
                break;
        }

    }

}
