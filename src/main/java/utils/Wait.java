package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class containing the methods to perform explicit waits
 */
public class Wait {

    private AppiumDriver driver;
    private static Wait instance;

    private Wait() {
        driver = (AppiumDriver) MobileManagement.getDriver();
    }

    public static Wait getInstance() {
        if (instance == null) {
            instance = new Wait();
        }
        return instance;
    }

    /**
     * Method that allows setting a wait according to a given condition and period of time
     *
     * @param condition
     * @param timeout
     */
    private void waitForCondition(ExpectedCondition<?> condition, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    /**
     * Method that expects a webelement to be visible for a certain time
     *
     * @param timeout
     * @param element
     */
    public void untilElementIsVisible(int timeout, WebElement element) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(element);
        waitForCondition(condition, timeout);
    }

    /**
     * This method wait for a page to complete loading
     *
     * @param timeout
     */
    public void untilPageLoads(int timeout) {
        ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        waitForCondition(condition, timeout);
    }

    /**
     * This method wait until the element is clickable by the locator.
     *
     * @param timeout
     * @param element
     */
    public void waitForClikableWebelement(int timeout, WebElement element) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(element);
        waitForCondition(condition, timeout);
    }

    /**
     * This method expects the given element to have an attribute with a specific value
     *
     * @param timeout
     * @param element
     * @param attribute
     * @param value
     */
    public void waitForAttributeValue(int timeout, WebElement element, String attribute, String value) {
        waitForCondition(wd -> element.getAttribute(attribute).equals(value), timeout);
    }

}
