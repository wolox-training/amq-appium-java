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

public class BrowsePage extends BasePage {

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(accessibility = "search")
    private MobileElement searchInput;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"browseSectionsList\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private List<MobileElement> browseSectionsList;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> browseResultTitleList;

    private String horizontalTopic = "//android.view.ViewGroup[@content-desc='%s']/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";

    public boolean isVisibleSearchInput() {
        return elementIsDisplayed(searchInput);
    }

    public void searchMovie(String movie) {
        searchInput.sendKeys(movie);
    }

    public List<String> sections() {
        return browseSectionsList.stream().map(MobileElement::getText).collect(Collectors.toList());
    }

    public List<String> resultList() throws NoSuchAlgorithmException {
        int indiceElement = SecureRandom.getInstanceStrong().nextInt(browseResultTitleList.size());

        indiceElement = (indiceElement == 0 ? indiceElement + 1 : indiceElement);
        String resultSearch = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[" + indiceElement + "]/android.view.ViewGroup";
        int count = 5;
        while (!(appiumDriver.findElementsByXPath(resultSearch.concat("/android.widget.TextView")).size() != 0) && count > 0) {
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

    public void selectMovieByTopic(String topicTitle, String topicElement) throws NoSuchAlgorithmException {
        wait.untilElementIsVisible(timeOutSeconds,searchInput);
        String element = String.format(horizontalTopic,topicElement);
        List<MobileElement> listMovies = appiumDriver.findElementsByXPath(element);
        int count=5;
        while(listMovies.isEmpty()&&count>0){
            SwipeHelper.scrollIntoView(topicTitle);
            count--;
            listMovies = appiumDriver.findElementsByXPath(element);
        }
        int indiceElement = SecureRandom.getInstanceStrong().nextInt(listMovies.size());
        listMovies.get(indiceElement).click();
    }
}
