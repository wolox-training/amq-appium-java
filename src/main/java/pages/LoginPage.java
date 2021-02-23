package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static dictionary.Constants.ERROR_INPUTS_LOGIN;
import static dictionary.Constants.LINK_FORGOT_PASS;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"userInput\"]")
    @AndroidFindBy(accessibility = "userInput")
    private MobileElement userInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@name=\"passwordInput\"]")
    @AndroidFindBy(accessibility = "passwordInput")
    private MobileElement passwordInput;

    @iOSXCUITFindBy(accessibility = "logInButton")
    @AndroidFindBy(accessibility = "logInButton")
    private MobileElement logInButton;

    @iOSXCUITFindBy(accessibility = LINK_FORGOT_PASS)
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='" + LINK_FORGOT_PASS + "']")
    private MobileElement lnkForgotPassword;

    @iOSXCUITFindBy(accessibility = ERROR_INPUTS_LOGIN)
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"passwordInput\"]/parent::android.view.ViewGroup/following-sibling::*[@class='android.widget.TextView']")
    private MobileElement errorInputCredentials;


    public void writeCredentials(String user, String pass) {
        userInput.sendKeys(user);
        passwordInput.sendKeys(pass);
    }

    public void clickLogIn() {
        appiumDriver.hideKeyboard();
        logInButton.click();
    }

    public String getMsgError() {
        return errorInputCredentials.getText();
    }

}
