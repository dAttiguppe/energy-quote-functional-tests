package com.test.testutils;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static com.test.constants.TestDataConstants.CHROME_DRIVER_PATH;
import static com.test.constants.TestDataConstants.FIREFOX_DRIVER_PATH;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class DriverManager {

    private static WebDriver driver;

    public static void init() throws Exception {
        String browser = System.getProperty("browser", "chrome");

        switch (browser) {

            case "chrome":
                driver = createChromeDriver();
                driver.manage().window().maximize();
                break;
            case "firefox":
                {
                    System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("marionette", true);
                    driver = new FirefoxDriver();
                break;
                }
            default:
                throw new AssertionError("Invalid browser: " + browser);
        }

    }


    private static ChromeDriver createChromeDriver() throws Exception {
        WebDriverManager.getInstance(CHROME).setup();
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }

    private static FirefoxDriverManager createFirefoxDriver() throws Exception{
        WebDriverManager.getInstance(FIREFOX).setup();
        System.setProperty("webdriver.firefox.driver", FIREFOX_DRIVER_PATH);
        return new FirefoxDriverManager();
    }

    public static ChromeOptions chromeDisablePopUps() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("--disable-notifications");

        return options;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quit() {
        driver = null;
    }

    public static void embedScreenshot(Scenario scenario) {
        scenario.embed(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
    }

    public static void embedScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario);
        }
    }
}
