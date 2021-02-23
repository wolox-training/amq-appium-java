package utils;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.HashMap;

import static dictionary.Constants.SCROLL_DUR;

public class SwipeHelper {

    private static Dimension windowSize;
    private static Dotenv dotenv = Dotenv.load();

    public static MobileElement androidHorizonalSwipe(String carousel, String option) {
        return MobileManagement.getDriver().findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(" + carousel + ").setAsHorizontalList()." +
                        "scrollIntoView(" + option + ")"));
    }

    public static MobileElement androidVerticalSwipe(String carousel, String option) {
        return MobileManagement.getDriver().findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(" + carousel + ").setAsVerticalList()." +
                        "scrollIntoView(" + option + ")"));
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public static void scrollDown() {
        Dimension dimension = MobileManagement.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions) MobileManagement.getDriver())
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }


    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public static void scrollAndClick(WebElement el) {
        int retry = 0;
        while (retry <= 5) {
            try {
                el.click();
                break;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                scrollDown();
                retry++;
            }
        }
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public static void scrollTo(String Text) {
        if (MobileManagement.getDriver() instanceof AndroidDriver<?>) {
            scrollIntoView(Text);
        } else if (MobileManagement.getDriver() instanceof IOSDriver<?>) {
            final HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("predicateString", "value == '" + Text + "'");
            scrollObject.put("toVisible", "true");
            ((IOSDriver<?>) MobileManagement.getDriver()).executeScript("mobile: scroll", scrollObject);
        }
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public static void scrollIntoView(String Text) {
        //https://developer.android.com/reference/androidx/test/uiautomator/UiSelector
        String mySelector = "new UiSelector().text(\"" + Text + "\").instance(0)";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + mySelector + ");";
        ((AndroidDriver<?>) MobileManagement.getDriver()).findElementByAndroidUIAutomator(command);
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public static void scroll(ScrollDirection dir, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) ((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int) ((size.height * distance) * 0.5);
        int left = midPoint.x - (int) ((size.width * distance) * 0.5);
        int right = midPoint.x + (int) ((size.width * distance) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), Duration.ofMillis(Integer.parseInt(dotenv.get(SCROLL_DUR))));
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), Duration.ofMillis(Integer.parseInt(dotenv.get(SCROLL_DUR))));
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), Duration.ofMillis(Integer.parseInt(dotenv.get(SCROLL_DUR))));
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), Duration.ofMillis(Integer.parseInt(dotenv.get(SCROLL_DUR))));
        }
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    private static Dimension getWindowSize() {
        if (windowSize == null) {
            windowSize = MobileManagement.getDriver().manage().window().getSize();
        }
        return windowSize;
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    protected static void swipe(Point start, Point end, Duration duration) {
        boolean isAndroid = MobileManagement.getDriver() instanceof AndroidDriver<?>;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(Integer.parseInt(dotenv.get("ANDROID_SCROLL_DIVISOR")));
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver<?>) MobileManagement.getDriver()).perform(ImmutableList.of(swipe));
    }

    /**
     * Method of https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS/blob/master/src/main/java/base/Util.java
     */
    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

}
