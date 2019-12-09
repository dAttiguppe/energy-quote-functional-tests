package com.test.elements.detailsPage;

import com.test.testutils.DriverManager;
import com.test.testutils.SeleniumDriverHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static com.test.constants.TestDataConstants.TARIFF_TYPE;
import static com.test.testutils.SeleniumDriverHelper.*;

public class UserPreferences {

    private WebDriver webDriver;

    public UserPreferences(){
        this.webDriver = DriverManager.getDriver();
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
