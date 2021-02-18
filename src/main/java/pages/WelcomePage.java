package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.MobileManagement;

public class WelcomePage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"continueAsGuest\"]/android.widget.TextView")
    private MobileElement continueAsGuest;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "logIn")
    private MobileElement btnLogin;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "signUp")
    private MobileElement btnSignUp;

    public void clickLogin() {
        btnLogin.click();
    }

    public void clickSignUp() {
        btnSignUp.click();
    }

    public boolean isOpenApp() {
        return MobileManagement.getDriver().isAppInstalled("com.azhavrid.movieswiperr");
    }

}
