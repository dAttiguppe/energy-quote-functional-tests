package com.test.elements.detailsPage;

import com.test.elements.detailsPage.PaymentTypes;
import com.test.elements.detailsPage.TariffTypeSection;
import com.test.testutils.DriverManager;
import com.test.testutils.SeleniumDriverHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.HashMap;

import static com.test.constants.TestDataConstants.TARIFF_TYPE;
import static com.test.testutils.SeleniumDriverHelper.*;

public class UserPreferences {

    private WebDriver webDriver;

    public UserPreferences(){
        this.webDriver = DriverManager.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this);
    }


    public void selectTariffType(HashMap<String,String> preferredTariff){
        TariffTypeSection tariffType = new TariffTypeSection();
        tariffType.chooseTariff(preferredTariff.get(TARIFF_TYPE));

    }

    public void selectPaymentType(String paymentMode){
        PaymentTypes paymentTypes = new PaymentTypes();
        paymentTypes.choosePaymentType(paymentMode);
    }


    public void setEmailID(String emailID) {
        scrollIntoElementUsingJs(findElementByCss("#Email"));
        findElementByCss("#Email").sendKeys("test@test.com");
        clickAndHoldSendKeysEnter();

        //Include assert for greeb tick

    }



    public void acceptTermsAndConditions() {
        scrollIntoElementUsingJs(findElementByCss("#terms"));
        clickElementUsingJs(findElementByCss("#terms"));

        clickAndHoldSendKeysEnter();

        Assert.assertTrue(SeleniumDriverHelper.isElementPresent(By.cssSelector("#TnC > div > div > span")));
    }
}
