package com.test.elements.energyDetails;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.DriverManager;
import com.test.testutils.SeleniumDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static com.test.testutils.SeleniumDriverHelper.*;

public class ElectricityUsage {

    private static WebDriver webDriver;

    public void clickNExtButton(){
         nextButton.click();
    }

    @FindBy(css= "button#goto-your-energy")
    public HtmlElement nextButton;

    public String electricityUsagekWHLocator = "#electricity-usage";
    public String dayTimeElectricityUsed = "#economy-7-day-usage";
    public String usageDayDropdownLocator = "#economy-7-day-usage-dropdown";
    public String nightTimeElectricityUsed = "#economy-7-night-usage";
    public String usageNightDropdownLocator = "#economy-7-night-usage-dropdown";

    public String dateOnBill = "#electricity-bill-day";
    public String datePicker = "#electricity-bill-date-field > div > div > span.date-picker-icon.datepicker-elec-icon";

    public String poundSpendLocator = "#poundSpend";
    public String electricitySpend = "#electricity-spend";
    public String electricitySpendDropDownLocator = "#spend-dropdown";

    public final String electricityUsage = "1000";
    public final String gasUsage = "1000";

    public ElectricityUsage(){
        this.webDriver = DriverManager.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this);

    }


    public void setDayTimeElectricityUsed() {
        findElementByCss(dayTimeElectricityUsed).sendKeys("100");
    }

    public void setNightTimeElectricityUsed() {
        findElementByCss(nightTimeElectricityUsed).sendKeys("100");
    }

    public void setPoundSpend() {
        findElementByCss(electricitySpend).sendKeys("100");
    }

    public void setDateOnBill() {
        findElementByCss("#electricity-bill-day_root > div > div > div > div > div.picker__footer > button.picker__button--today")
        .click();
    }

    public void setElectricityUsagekWH(RunTimeExpectedData runTimeExpectedData) {

        if (runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y")) {

            runTimeExpectedData.setElectricityUsage(electricityUsage);
            runTimeExpectedData.setGasUsage(gasUsage);
        }

        if(findElementByCss("#your-energy > section:nth-child(2) > div.section-header > h1").getText()
                                    .equalsIgnoreCase("Your gas")){
            findElementByCss("#gas-usage").sendKeys("100");
            waitForAjax(1000);

        }

        else
            setValueUsingJs(findElementByCss(electricityUsagekWHLocator),"100");


        WebElement webElement = findElementByCss(electricityUsagekWHLocator);
        Select paymentFrequency = new Select(findElementByCss("#usage-dropdown"));
        paymentFrequency.selectByVisibleText("Annually");
        waitForAjax(1000);
        paymentFrequency.selectByVisibleText("Monthly");
        waitForAjax(1000);
        paymentFrequency.selectByVisibleText("Annually");
        waitForAjax(1000);

        findElementByCss(electricityUsagekWHLocator).sendKeys("100");

        webElement.sendKeys(Keys.TAB);

        webElement.sendKeys(Keys.TAB);

        requiredWait(5000);
        webElement.sendKeys(Keys.TAB);

        clickNExtButton();

        //TODO findElementByCss(electricityUsagekWHLocator).sendKeys("1000");findElementByCss(electricityUsagekWHLocator).sendKeys(Keys.TAB);findElementByCss(electricityUsagekWHLocator).click();

        requiredWait(5000);

    }

    private void enterUsageData(RunTimeExpectedData runTimeExpectedData) {
        scrollIntoElementUsingJs(findElementByCss("#gas-usage"));

        //waitForElementToBeClickable(By.cssSelector(electricityUsagekWHLocator),100);
        //clickElementUsingJs(findElementByCss(electricityUsagekWHLocator));





        findElementByCss(electricityUsagekWHLocator).sendKeys(electricityUsage);
        runTimeExpectedData.setElectricityUsage(electricityUsage);

        scrollIntoElementUsingJs(findElementByCss("#usage-dropdown"));
        Select paymentFrequency = new Select(findElementByCss("#usage-dropdown"));
        paymentFrequency.selectByVisibleText("Annually");

        //Assert.assertTrue(isElementPresent(By.cssSelector("#gas-type-of-bill-question > div > div > div.radio-buttons.radio-buttons-small > span")));
//        clickAndHoldSendKeysEnter();
    }

    public void clickNextButton(){
        WebElement element= webDriver.findElement(By.cssSelector("#goto-your-energy"));
        Actions action = new Actions(webDriver);
        action.moveToElement(element).perform();
        action.moveToElement(element).click().perform();

    }

    public void getEnergySpendField(){
//        ru.yandex.qatools.htmlelements.element.TextInput kWHSpend =
//                new ru.yandex.qatools.htmlelements.element.TextInput(findElementByCss("button#goto-your-energy"));
//        //kWHSpend.findElement(By.cssSelector("")).sendKeys("100");
//        kWHSpend.getWrappedElement().sendKeys("1000");

        ru.yandex.qatools.htmlelements.element.Button nextButtonPrblm =
                new ru.yandex.qatools.htmlelements.element.Button(findElementByCss("button#goto-your-energy"));
        nextButtonPrblm.click();

    }



}
