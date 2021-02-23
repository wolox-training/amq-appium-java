package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class WelcomePage extends BasePage {

    @iOSXCUITFindBy(accessibility = "continueAsGuest")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"continueAsGuest\"]/android.widget.TextView")
    private MobileElement continueAsGuest;

    @iOSXCUITFindBy(accessibility = "logIn")
    @AndroidFindBy(accessibility = "logIn")
    private MobileElement btnLogin;

    @iOSXCUITFindBy(accessibility = "signUp")
    @AndroidFindBy(accessibility = "signUp")
    private MobileElement btnSignUp;

    public void clickLogin() {
        btnLogin.click();
    }

    public void clickSignUp() {
        btnSignUp.click();
    }

}
