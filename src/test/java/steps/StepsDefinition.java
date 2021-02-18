package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.WelcomePage;

import static dictionary.Constants.LOGIN_BUTTON;
import static dictionary.Constants.SIGN_UP_BUTTON;

public class StepsDefinition {

    private WelcomePage welcomePage;

    @Given("I am in the Movie Swiper app")
    public void iAmInTheMovieSwiperApp() {
        welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isOpenApp());
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        switch (button) {
            case LOGIN_BUTTON:
                welcomePage.clickLogin();
                break;
            case SIGN_UP_BUTTON:
            default:
                welcomePage.clickSignUp();
        }
    }
}
