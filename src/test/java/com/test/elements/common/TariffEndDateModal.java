package com.test.elements.common;

import org.openqa.selenium.WebElement;

import java.util.List;

import static com.test.testutils.SeleniumDriverHelper.*;

public class TariffEndDateModal {

    public String tariffEndDateModalLocator = "#tariff-end-date-modal";
    public String modalText = "Help us to improve your results";

    public String gasTariffExpiryDateLocator = "#gas-end-date-unknown";
    public String elecTariffExpiryDateLocator ="#electricity-end-date-unknown";
    public String updateResultsLocator = "#tariff-end-date-update-results-button";

    public static Boolean isPresent;

    public boolean isTariffModalPresent(){
        waitForAjax(1000);
        List<WebElement> modalLocator = findElementsByCss(tariffEndDateModalLocator);
        if(modalLocator.size()>0)
            isPresent=true;
        else
            isPresent=false;
        return isPresent;
    }

    public void clickGasTariffExpiryDateLocator() {
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss(gasTariffExpiryDateLocator));
    }

    public void clickElecTariffExpiryDateLocator() {
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss(elecTariffExpiryDateLocator));
    }

    public void clickUpdateResultsButton() {
        waitForAjax(1000);
        scrollIntoElementUsingJs(findElementByCss(updateResultsLocator));
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss(updateResultsLocator));
    }

}
