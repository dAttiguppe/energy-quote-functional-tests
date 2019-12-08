package com.test.elements.detailsPage;

import com.test.testutils.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static com.test.testutils.SeleniumDriverHelper.*;

public class AvailableTariffs {

    private WebDriver driver;

    public String moreTariffsButton = "//*[@id=\"filters-more-tariffs-view-input\"]";
    public String includeProvidersCantSwitcho = "#nonSwitchableCheckbox > span";

    public final String providersNotAbleToSwitchTo = "We can’t switch you to this tariff";
    public String allOurTariffs = "#all_our_tariffs_header";

    public AvailableTariffs(){
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(com.test.testutils.DriverManager.getDriver())),
                this);
    }

    public void clickMoreTariffsSection(){
        scrollToElementUsingJs(findElementByCss("#filters-more-tariffs-view-input"));
        clickElementUsingJs(findElementByXpath(moreTariffsButton));
    }

    public void clickIncludeProvidersCantSwitchTo(){
        findElementByCss(includeProvidersCantSwitcho).click();
    }

    public List<WebElement> getAvailableTariffResults() {
        return findElementsByCss("#tariffs-for-me > div > section > table > tbody");
    }

    public boolean isAllOurTariffsPresent(){
        return findElementByCss(allOurTariffs).isDisplayed();
    }

    public void verifyTariffsIncludeProvidersCantSwitchTo(){
        List<WebElement> availableTariffs = getAvailableTariffResults();
        Assert.assertTrue(availableTariffs.stream().
                anyMatch(s->s.findElement(By.cssSelector("td.td.more-details")).getText().contains(providersNotAbleToSwitchTo)));

        getAvailableTariffResults().stream().
                anyMatch(s->s.findElement(By.cssSelector("td.td.more-details")).getText().contains("can"));
    }

}
