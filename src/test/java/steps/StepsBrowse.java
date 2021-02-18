package steps;

import dtos.MovieData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BrowsePage;
import pages.MovieDataPage;
import utils.DataManager;

import java.util.List;

public class StepsBrowse {

    private BrowsePage browsePage;
    private MovieData movieData;

    @When("I am looking for a movie {string}")
    public void iAmLookingForAMovie(String movie) {
        browsePage = new BrowsePage();
        browsePage.searchMovie(movie);
    }

    @And("I select one from the list of results")
    public void iSelectOneFromTheListOfResults() {
        List<String> movie = browsePage.resultList();
        movieData = DataManager.getMovieInformation();
        movieData.setTitleMovie(movie.get(0));
        movieData.setScoreMovie(movie.get(1));
        movieData.setYearMovie(movie.get(2));
    }

    @Then("the system displays the information of the selected movie")
    public void theSystemDisplaysTheInformationOfTheSelectedMovie() {
        MovieDataPage movieDataPage = new MovieDataPage();
        Assert.assertEquals(movieData.getTitleMovie(), movieDataPage.getTitleMovie());
        Assert.assertEquals(movieData.getScoreMovie(), movieDataPage.getScoreMovie());
        Assert.assertEquals(movieData.getYearMovie(), movieDataPage.getYearMovie());
    }
}
