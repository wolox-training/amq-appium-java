package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MovieDataPage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]")
    private MobileElement titleMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement scoreMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[3]")
    private MobileElement yearMovie;

    public String getTitleMovie() {
        return titleMovie.getText();
    }

    public String getScoreMovie() {
        return scoreMovie.getText();
    }

    public String getYearMovie() {
        return yearMovie.getText();
    }

}
