package steps;

import dtos.MovieData;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LibraryPage;
import pages.MovieDataPage;
import pages.components.MenuBar;
import utils.DataManager;

import static dictionary.Constants.MY_FAVORITE;
import static dictionary.Constants.MY_WATCHLIST;

public class StepsLibrary {

    private MovieData movieData = DataManager.getMovieInformation();
    LibraryPage libraryPage = new LibraryPage();

    @Then("The movie is going to {string}")
    public void theMovieIsGoingTo(String option) {
        MenuBar menuBar = new MenuBar();
        menuBar.clicLibraryMeny();
        if (option.equals(MY_WATCHLIST)) {
            libraryPage.clickWatchList();
            validateMovieInformation();
        } else if (option.equals(MY_FAVORITE)) {
            libraryPage.clickFavorite();
            validateMovieInformation();
        }
        removeMovieFromList();
    }

    public void validateMovieInformation() {
        Assert.assertEquals(movieData.getTitleMovie(), libraryPage.getTitleMovie());
        Assert.assertEquals(movieData.getScoreMovie(), libraryPage.getScoreMovie());
        Assert.assertEquals(movieData.getYearMovie(), libraryPage.getYearMovie());
    }

    public void removeMovieFromList() {
        libraryPage.clickOnMovie();

        MovieDataPage movieDataPage = new MovieDataPage();
        if (movieDataPage.isVisibleFavoriteButton()) {
            movieDataPage.clickFavoriteButton();
        } else {
            movieDataPage.clickUnSaveButton();
        }
    }
}
