package com.test.testutils;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static com.test.testutils.DriverManager.getDriver;
import static com.test.testutils.SystemPropertyHelper.isTeardown;

public class SeleniumDriverHelper {

    public static void waitForAjax(long maximumWaitMiliseconds, boolean doubleCheckAjaxRequest) {
        WebDriver driver = getDriver();
        long endTime = System.currentTimeMillis() + maximumWaitMiliseconds;

        while (true) {
            long currentTime = System.currentTimeMillis();

            if (currentTime > endTime) {
                break;
            }

            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");

            if (ajaxIsComplete && doubleCheckAjaxRequest) {
                requiredWait(100);
                doubleCheckAjaxRequest = false;
            } else if (ajaxIsComplete) {
                requiredWait(100);
                break;
            }

            requiredWait(100);
        }
    }

    public static void waitForAjax(long maximumWaitMiliseconds) {
        waitForAjax(maximumWaitMiliseconds, false);
    }

    public static void requiredWait(long waitTimeOut) {
        try {
            Thread.sleep(waitTimeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void implicitWait(long time) {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void waitForPageLoad() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 3000);

        Predicate<WebDriver> pageLoaded = input -> ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
        wait.until((Function<? super WebDriver, Boolean>) pageLoaded::test);
        requiredWait(5000);
    }

    public static void waitForAjax(long maximumWaitMiliseconds, boolean doubleCheckAjaxRequest, long timeoutForPrechekcingAjax) {
        requiredWait(timeoutForPrechekcingAjax);
        waitForAjax(maximumWaitMiliseconds, doubleCheckAjaxRequest);
    }


    public static void scrollToWebElement(WebElement element) {
        if (!element.isDisplayed()) {
            scroll(element);
        }
    }

    private static void scroll(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        Action action = actions.build();
        action.perform();
    }

    public static boolean isElementPresent(By by) {
        boolean isElementPresent;
        WebDriver driver = getDriver();

        try {
            driver.findElement(by);
            isElementPresent = true;
        } catch (NoSuchElementException e) {
            isElementPresent = false;
        }

        return isElementPresent;
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        return waitForElementToBeClickable(locator, 20);
    }

    public static WebElement waitForElementToBeClickable(By locator, long time) {
        WebDriver driver = getDriver();
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementToBeClickable(WebElement element, long time) {
        WebDriver driver = getDriver();
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollToElementUsingJs(WebElement element) {
        runScriptUsingJs("arguments[0].scrollIntoView();", element);
    }

    public static void scrollIntoElementUsingJs(WebElement element) {
        runScriptUsingJs("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoElementUsingJsAndClick(WebElement element) {
        scrollIntoElementUsingJs(element);
        element.click();
        requiredWait(1000);
    }

    public static void implicitlyWaitInSeconds(long time) {
        getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void waitUntilPresenceOfElementLocated(By by, long time) {
        WebDriver driver = getDriver();
        new WebDriverWait(driver, time).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitUntilPresenceOfElementLocated(By by) {
        waitUntilPresenceOfElementLocated(by, 75);
    }

    public static void waitUntilPresenceOfElementLocatedByCss(String selector) {
        waitUntilPresenceOfElementLocatedByCss(selector, 75);
    }

    public static void waitUntilPresenceOfElementLocatedByXpath(String selector) {
        waitUntilPresenceOfElementLocated(By.xpath(selector));
    }

    public static void waitUntilPresenceOfElementLocatedByXpath(String selector, long time) {
        waitUntilPresenceOfElementLocated(By.xpath(selector), time);
    }

    public static void waitUntilPresenceOfElementLocatedByCss(String selector, long time) {
        waitUntilPresenceOfElementLocated(By.cssSelector(selector), time);
    }

    public static WebElement findElement(By by) {
        WebDriver driver = getDriver();
        return driver.findElement(by);
    }

    public static WebElement findElementByCss(String locator) {
        return findElement(By.cssSelector(locator));
    }

    public static WebElement findElementByXpath(String locator) {
        return findElement(By.xpath(locator));
    }

    public static List<WebElement> findElements(By by) {
        WebDriver driver = getDriver();
        return driver.findElements(by);
    }

    public static List<WebElement> findElementsByCss(String locator) {
        return findElements(By.cssSelector(locator));
    }

    public static void quit() {
        getDriver().quit();
        DriverManager.quit();
    }

    public static void initialize() throws Exception {
        if (getDriver() == null) {
            DriverManager.init();
        }

    }

    public static void getUrl(String url) {
        getDriver().get(url);
    }

    public static void deleteAllCookies() {
        getDriver().manage().deleteAllCookies();
    }


    public static void clickElementUsingJs(WebElement element) {
        runScriptUsingJs("arguments[0].click();", element);
    }


    public static void runScriptUsingJs(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(script, args);
    }

    public static void setValueUsingJs(WebElement element, String value) {
        runScriptUsingJs("arguments[0].value=arguments[1]", element, value);
    }


    public static String getTextByXpath(String locator) {
        waitUntilPresenceOfElementLocatedByXpath(locator, 75);
        return ElementHelper.getTextFromElement(findElementByXpath(locator));
    }

    public static String getTextByCss(String locator) {
        waitUntilPresenceOfElementLocatedByCss(locator, 75);
        return ElementHelper.getTextFromElement(findElementByCss(locator));
    }

    private static void performAction(Action action) {
        SeleniumDriverHelper.requiredWait(500);
        action.perform();
        SeleniumDriverHelper.requiredWait(500);
    }

    private static Actions getActions() {
        return new Actions(getDriver());
    }

    public static void clickAndHoldSendKeysEnter() {
        performAction(getActions().clickAndHold().release().sendKeys(Keys.ENTER).build());
    }

    public static void clickAndHoldSendKeys(String text) {
        performAction(getActions().clickAndHold().release().sendKeys(text).build());
    }

    public static void tearDown() {
        if (isTeardown()) {
            quit();
        }
    }

}
