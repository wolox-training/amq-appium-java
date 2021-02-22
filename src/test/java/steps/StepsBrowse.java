package steps;

import dtos.MovieData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BrowsePage;
import pages.MovieDataPage;
import utils.DataManager;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static dictionary.Constants.*;

public class StepsBrowse {

    private BrowsePage browsePage = new BrowsePage();
    private MovieData movieData;

    @When("I am looking for a movie {string}")
    public void iAmLookingForAMovie(String movie) {
        browsePage.searchMovie(movie);
    }

    @And("I select one from the list of results")
    public void iSelectOneFromTheListOfResults() throws NoSuchAlgorithmException {
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

    @When("I select a movie from the {string}")
    public void iSelectAMovieFromThe(String topic) throws NoSuchAlgorithmException {
        System.out.println("topico recibido " + topic);
        switch (topic) {
            case TOPIC_DAILY:
                browsePage.selectMovieByTopic(topic, TOPICS.get(0));
                break;
            case TOPIC_WEEKLY:
                browsePage.selectMovieByTopic(topic, TOPICS.get(1));
                break;
            case TOPIC_POPULAR:
                browsePage.selectMovieByTopic(topic, TOPICS.get(2));
                break;
            case TOPIC_TOP_RATED:
            default:
                browsePage.selectMovieByTopic(topic, TOPICS.get(3));
        }
    }

    @And("I click on favorite")
    public void iClickOnFavorite() {
        MovieDataPage movieDataPage = new MovieDataPage();
        movieData = DataManager.getMovieInformation();
        movieData.setTitleMovie(movieDataPage.getTitleMovie());
        movieData.setScoreMovie(movieDataPage.getScoreMovie());
        movieData.setYearMovie(movieDataPage.getYearMovie());
        movieDataPage.clickFavoriteUnselected();
    }
}
