package pages.components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuBar extends BaseComponent {

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private MobileElement bottomBarBrowse;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private MobileElement bottomBarExplore;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private MobileElement bottomBarLibrary;


    public void clicBrowseMenu() {
        bottomBarBrowse.click();
    }

    public void clicExploreMenu() {
        bottomBarExplore.click();
    }

    public void clicLibraryMeny() {
        bottomBarLibrary.click();
    }


    public boolean isDisplayedMenu() {
        return bottomBarBrowse.isDisplayed() && bottomBarExplore.isDisplayed()
                && bottomBarLibrary.isDisplayed();
    }
}
