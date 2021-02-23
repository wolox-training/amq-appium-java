package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static dictionary.Constants.*;

/**
 * Class in charge of the initialization and configuration of the webdriver
 */
public class MobileManagement {

    protected static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();
    private static Dotenv dotenv = Dotenv.load();
    private static AppiumDriverLocalService server;

    private MobileManagement() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver.get() == null) {
            driver.set(initializeDriver());
        }
        return driver.get();
    }

    /**
     * Method that determines the platform_name to use and its configuration
     *
     * @return
     */
    private static AppiumDriver<MobileElement> initializeDriver() {
        AppiumDriver<MobileElement> driver = null;

        DesiredCapabilities capabilities = getCapabilities();
        String serverUrl = dotenv.get("SERVER_URL");

        try {
            switch (dotenv.get(PLATFORM_NAME)) {
                case PLATFORM_ANDROID:
                    driver = new AndroidDriver<MobileElement>(new URL(serverUrl), capabilities);
                    break;
                case PLATFORM_IOS:
                default:
                    driver = new IOSDriver<MobileElement>(new URL(serverUrl), capabilities);
                    break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static DesiredCapabilities getCapabilities() {
        File app;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
        String[] capabilitiesFile;
        if (dotenv.get(PLATFORM_NAME).equals(PLATFORM_ANDROID)) {
            app = new File(dotenv.get("APK_NAME"));
            capabilitiesFile = dotenv.get("CAPABILITY").split(";");
        } else {
            app = new File(dotenv.get("APK_NAME_IOS"));
            capabilitiesFile = dotenv.get("CAPABILITY_IOS").split(";");
        }
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        for (int i = 0; i < capabilitiesFile.length; i += 2) {
            capabilities.setCapability(capabilitiesFile[i], capabilitiesFile[i + 1]);
        }
        return capabilities;
    }

    public static void startServer() {
        server = AppiumDriverLocalService.buildDefaultService();
        server.start();
    }

    public static void stopServer() {
        getDriver().closeApp();
        getDriver().quit();
        server.stop();
        driver.remove();
    }

    public static boolean isInstalledApp() {
        return MobileManagement.getDriver().isAppInstalled(dotenv.get("BUNDLE_ID"));
    }
}
