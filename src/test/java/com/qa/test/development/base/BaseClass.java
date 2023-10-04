package com.qa.test.development.base;

import com.google.common.collect.ImmutableMap;
import com.qa.test.development.utils.Utils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    protected static AppiumDriver driver;
    protected static Properties properties;
    protected static Properties propertiesTestData;

    String platform;

    public BaseClass() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Parameters({"platformName", "platformVersion", "deviceUDID"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceUDID) {
        try {
            String file =
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "qa" + File.separator + "test" + File.separator + "development" + File.separator + "resources" + File.separator + "config.properties";
            InputStream inputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(inputStream);
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
            String appURL = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "qa" + File.separator + "test" + File.separator + "development" + File.separator + "application" + File.separator + properties.getProperty("androidSauceLabsOldAppLocation");

            //desiredCapabilities.setCapability(MobileCapabilityType.APP, appURL);
            desiredCapabilities.setCapability("appPackage", properties.getProperty("androidSwagLabOldAppPackage"));
            desiredCapabilities.setCapability("appActivity", properties.getProperty("androidSwagLabOldAppActivity"));

            URL url = new URL(properties.getProperty("appiumURL"));
            driver = new AndroidDriver(url, desiredCapabilities);

            String testDataFile =
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "qa" + File.separator + "test" + File.separator + "development" + File.separator + "resources" + File.separator + "testData.properties";
            InputStream inputFile = new FileInputStream(testDataFile);
            propertiesTestData = new Properties();
            propertiesTestData.load(inputFile);
            platform = platformName;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Exception Caught:" + e);
        }
    }

    public void waitForElement(WebElement element) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(Utils.wait));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void click(WebElement element) {
        try {
            waitForElement(element);
            element.click();
        } catch (Exception e) {
            System.out.println("Click Method Exception" + e);
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            waitForElement(element);
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("SendKeys Method Exception" + e);
        }

    }

    public String attribute(WebElement element, String attribute) {
        try {
            waitForElement(element);
        } catch (Exception e) {
            System.out.println("Attribute Method Exception" + e);
        }
        return element.getAttribute(attribute);
    }

    public void clear(WebElement element) {
        try {
            waitForElement(element);
        } catch (Exception e) {
            System.out.println("Clear Method Exception" + e);
        }
        element.clear();
    }

    public void closeApp() {
        switch (platform) {
            case "Android": {
                ((InteractsWithApps) driver).terminateApp(properties.getProperty("androidSwagLabOldAppPackage"));
                break;
            }
            default:
                System.out.println("Enter valid platform");
        }
    }

    public void openApp() {
        switch (platform) {
            case "Android": {
                ((InteractsWithApps) driver).activateApp(properties.getProperty("androidSwagLabOldAppPackage"));
                break;
            }
            default:
                System.out.println("Enter valid platform");
        }
    }

    public void scrollableOld(WebElement element, double percentageValue) {
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down",
                "percent", percentageValue
        ));
    }

//    public void scrollable(WebDriver driver) {
//        driver.findElement(AppiumBy.androidUIAutomator("new UIScrollable(new UiSelector()" + ".description
//        (\"<parent_locator>")).scrollIntoView("+ " new UISelector().description(\"<child_locator>\"));");
//    }

    @AfterTest
    public void afterTest() {
        driver.quit();

    }
}
