package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MovieDataPage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]")
    private MobileElement titleMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement scoreMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[3]")
    private MobileElement yearMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"favButtonUnselected\"]/android.widget.TextView[1]")
    private MobileElement favButtonUnselected;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"favButton\"]/android.widget.TextView[1]")
    private MobileElement favButton;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"favButtonUnselected\"]/preceding::android.view.ViewGroup[1]")
    private MobileElement unSaveButton;

    public String getTitleMovie() {
        return titleMovie.getText();
    }

    public String getScoreMovie() {
        return scoreMovie.getText();
    }

    public String getYearMovie() {
        return yearMovie.getText();
    }

    public void clickFavoriteUnselected() {
        favButtonUnselected.click();
    }

    public void clickFavoriteButton() {
        favButton.click();
    }

    public void clickUnSaveButton() {
        unSaveButton.click();
    }

    public boolean isVisibleFavoriteButton() {
        return elementIsDisplayed(favButton);
    }

}
