package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "userInput")
    private MobileElement userInput;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "passwordInput")
    private MobileElement passwordInput;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "logInButton")
    private MobileElement btnlogInButton;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot the password?']")
    private MobileElement lnkForgotPassword;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"passwordInput\"]/parent::android.view.ViewGroup/following-sibling::*[@class='android.widget.TextView']")
    private MobileElement errorInputCredentials;


    public void writeCredentials(String user, String pass) {
        userInput.sendKeys(user);
        passwordInput.sendKeys(pass);
    }

    public void writeValidateCredentials() {
        String user = dotenv.get("USERNAME");
        String pass = dotenv.get("PASSWORD");
        userInput.sendKeys(user);
        passwordInput.sendKeys(pass);
    }

    public void clickLogIn() {
        btnlogInButton.click();
    }

    public String getMsgError() {
        return errorInputCredentials.getText();
    }

}
