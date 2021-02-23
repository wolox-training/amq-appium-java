package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.MobileManagement;

public class Hooks {

    @Before
    public void openApp() {
        MobileManagement.getDriver().launchApp();
    }

    @After
    public void resetApp() {
        MobileManagement.getDriver().closeApp();
    }
}
