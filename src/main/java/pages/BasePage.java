package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.support.PageFactory;
import utils.MobileManagement;
import utils.Wait;

import java.time.Duration;

public class BasePage {

    protected AppiumDriver<MobileElement> appiumDriver;
    protected Wait wait;
    protected Dotenv dotenv;
    protected int timeOutSeconds;

    public BasePage() {
        dotenv = Dotenv.load();
        appiumDriver = MobileManagement.getDriver();
        wait = Wait.getInstance();
        timeOutSeconds = Integer.parseInt(dotenv.get("TIME_SECONDS"));
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(timeOutSeconds)), this);
    }

}
