package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.SwipeHelper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dictionary.Constants.PLATFORM_ANDROID;
import static dictionary.Constants.PLATFORM_NAME;

public class BrowsePage extends BasePage {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"search\"]")
    @AndroidFindBy(accessibility = "search")
    private MobileElement searchInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"browseSectionsList\"]/XCUIElementTypeScrollView//XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"browseSectionsList\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private List<MobileElement> browseSectionsList;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> browseResultTitleList;

    private String resultSearch = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup['%s']/android.view.ViewGroup";


    public void searchMovie(String movie) {
        wait.untilElementIsVisible(timeOutSeconds, browseSectionsList.get(0));
        searchInput.sendKeys(movie);
    }

    public List<String> sections() {
        return browseSectionsList.stream().map(MobileElement::getText).collect(Collectors.toList());
    }

    public List<String> resultList() throws NoSuchAlgorithmException {
        if (dotenv.get(PLATFORM_NAME).equals(PLATFORM_ANDROID)) {
            return resultListAndroid();
        } else {
            return resultListIOS();
        }
    }

    private List<String> resultListAndroid() throws NoSuchAlgorithmException {
        int indexElement = SecureRandom.getInstanceStrong().nextInt(browseResultTitleList.size());

        indexElement = (indexElement == 0 ? indexElement + 1 : indexElement);
        resultSearch = String.format(resultSearch, indexElement);
        int count = 5;
        while (appiumDriver.findElementsByXPath(resultSearch.concat("/android.widget.TextView")).size() == 0 && count > 0) {
            SwipeHelper.scrollDown();
            count--;
        }
        MobileElement title = appiumDriver.findElementByXPath(String.format("%s/android.widget.TextView[1]", resultSearch));
        MobileElement score = appiumDriver.findElementByXPath(String.format("%s/android.widget.TextView[2]", resultSearch));
        MobileElement year = appiumDriver.findElementByXPath(String.format("%s/android.widget.TextView[3]", resultSearch));

        List<String> infoMovie = new ArrayList<>();
        infoMovie.add(title.getText());
        infoMovie.add(score.getText());
        infoMovie.add(year.getText());

        title.click();

        return infoMovie;
    }

    private List<String> resultListIOS() {
        for (MobileElement mobileElement : browseResultTitleList) {
            System.out.println("item: " + mobileElement.getText());
        }
        List<String> infoMovie = new ArrayList<>();
        infoMovie.add(browseResultTitleList.get(1).getText());
        infoMovie.add(browseResultTitleList.get(2).getText());
        infoMovie.add(browseResultTitleList.get(3).getText());

        return infoMovie;
    }

}
