package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.SwipeHelper;

import static utils.SwipeHelper.ScrollDirection.*;

public class ExplorePage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageView")
    private MobileElement movieImage;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup//android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView/following-sibling::android.view.ViewGroup/android.widget.TextView[1]")
    private MobileElement titleMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup//android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView/following-sibling::android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement scoreMovie;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup//android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView/following-sibling::android.view.ViewGroup/android.widget.TextView[3]")
    private MobileElement yearMovie;

    public void clickImageMovie() {
        movieImage.click();
    }

    public String getTitleMovie() {
        wait.untilElementIsVisible(timeOutSeconds, titleMovie);
        return titleMovie.getText();
    }

    public String getScoreMovie() {
        return scoreMovie.getText();
    }

    public String getYearMovie() {
        return yearMovie.getText();
    }

    public void skipMovie() {
        SwipeHelper.swipeElementAndroid(movieImage, LEFT);
    }

    public void saveMovie() {
        SwipeHelper.swipeElementAndroid(movieImage, RIGHT);
    }

    public void likeMovie() {
        SwipeHelper.swipeElementAndroid(movieImage, UP);
    }

}
