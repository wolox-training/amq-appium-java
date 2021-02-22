package steps;

import dtos.MovieData;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LibraryPage;
import pages.MovieDataPage;
import pages.MyFavoritePage;
import pages.components.MenuBar;
import utils.DataManager;

public class StepsFavorite {

    private MovieData movieData= DataManager.getMovieInformation();

    @Then("The movie is added to my favorites")
    public void theMovieIsAddedToMyFavorites() {
        MenuBar menuBar = new MenuBar();
        menuBar.clicLibraryMeny();

        LibraryPage libraryPage = new LibraryPage();
        libraryPage.clickFavorite();

        MyFavoritePage myFavoritePage = new MyFavoritePage();
        Assert.assertEquals(movieData.getTitleMovie(),myFavoritePage.getTitleMovie());
        Assert.assertEquals(movieData.getScoreMovie(),myFavoritePage.getScoreMovie());
        Assert.assertEquals(movieData.getYearMovie(),myFavoritePage.getYearMovie());

        myFavoritePage.clickOnMovie();

        MovieDataPage movieDataPage = new MovieDataPage();
        movieDataPage.clickFavoriteButton();

    }
}
