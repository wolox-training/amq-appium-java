package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LibraryPage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "myFavorite")
    private MobileElement favoriteMenu;

    public void clickFavorite(){
        wait.untilElementIsVisible(timeOutSeconds,favoriteMenu);
        favoriteMenu.click();
    }
}
