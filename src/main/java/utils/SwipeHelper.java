package utils;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.HashMap;

public class SwipeHelper {

    private static Dimension windowSize;
    private static int ANDROID_SCROLL_DIVISOR = 3;
    private static Duration SCROLL_DUR = Duration.ofMillis(1000);

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
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    /**
     * Method of http://appium.io/docs/en/writing-running-appium/tutorial/swipe/simple-element/
     *
     * @param el
     * @param dir
     */
    public static void swipeElementAndroid(MobileElement el, ScrollDirection dir) {
        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder;
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Rectangle rect = el.getRect();
        // sometimes it is needed to configure edgeBorders
        // you can also improve borders to have vertical/horizontal
        // or left/right/up/down border variables
        edgeBorder = 0;

        switch (dir) {
            case DOWN: // from up to down
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                break;
            case UP: // from down to up
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                break;
            case LEFT: // from right to left
                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT: // from left to right
                pointOptionStart = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(MobileManagement.getDriver())
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
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
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
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
