package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BrowsePage;
import pages.LoginPage;

import static dictionary.Constants.ERROR_INPUTS_LOGIN;

public class StepsLogin {

    private LoginPage loginPage = new LoginPage();

    @When("I write the {string} and {string}")
    public void iWriteTheAnd(String user, String pass) {
        loginPage.writeCredentials(user, pass);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogIn();
    }

    @Then("the system displays the {string} message")
    public void theSystemDisplaysTheMessage(String msg) {
        if (msg.equals("error")) {
            Assert.assertEquals(ERROR_INPUTS_LOGIN, loginPage.getMsgError());
        } else {
            BrowsePage browsePage = new BrowsePage();
            for (String text : browsePage.sections()) {
                System.out.println("Categoría: " + text);
            }
        }
    }

    @And("I authenticate the page")
    public void iAuthenticateThePage() {
        loginPage.writeValidateCredentials();
        iClickTheLoginButton();
    }


}
