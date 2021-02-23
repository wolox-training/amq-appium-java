package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.WelcomePage;
import utils.MobileManagement;

import static dictionary.Constants.*;

public class StepsDefinition {

    private WelcomePage welcomePage;

    @Given("I am in the Movie Swiper app")
    public void iAmInTheMovieSwiperApp() {
        welcomePage = new WelcomePage();
        Assert.assertTrue(MobileManagement.isInstalledApp());
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        switch (button) {
            case LOGIN_BUTTON:
                welcomePage.clickLogin();
                break;
            case SIGN_UP_BUTTON:
                welcomePage.clickSignUp();
                break;
            default:
                Assert.fail(NOT_EXPECTED_OPTION);
        }
    }
}
