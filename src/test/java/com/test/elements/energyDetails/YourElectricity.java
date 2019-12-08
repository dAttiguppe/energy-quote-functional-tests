package com.test.elements.energyDetails;

import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

import static com.test.constants.TestDataConstants.*;
import static com.test.testutils.SeleniumDriverHelper.*;

public class YourElectricity {

    public String electricityTariffDropdownLocator = "#electricity-tariff-additional-info";
    public String economy7MeterLocatorYes = "#economy-7-yes";
    public String economy7MeterLocatorNo = "#economy-7-no";
    public String electricityMainSourceOfHeatingYes = "#electricity-main-heating-yes";
    public String electricityMainSourceOfHeatingNo = "#electricity-main-heating-no";



    public static WebDriver driver;

    public YourElectricity() throws Exception{
        this.driver = DriverManager.getDriver();
    }


    public void setElectricityTariffDropdownValue(String electricityTariff){

        Select tariffValue = new Select(findElement(By.xpath("//*[@id=\"electricity-tariff-additional-info\"]")));
        tariffValue.selectByVisibleText(electricityTariff);
    }

    public void setEconomy7MeterValue(String economyMeter) {
        if(economyMeter.equalsIgnoreCase("No"))
            clickElementUsingJs(findElementByCss(economy7MeterLocatorNo));
        else
            clickElementUsingJs(findElementByCss(economy7MeterLocatorYes));

    }

    public void setElectricityUsageData(HashMap<String, String> electricityUsageData, RunTimeExpectedData runTimeExpectedData) throws InterruptedException {
        setElectricityTariffDropdownValue(electricityUsageData.get(ELECTRICITY_TARIFF));
        setEconomy7MeterValue(electricityUsageData.get(ECONOMY_7METER));

        setPaymentMode(electricityUsageData.get(PAYMENT_MODE));
        setMainSourceOfHeating(electricityUsageData.get(MAIN_SOURCE_OF_HEATING));

        setElectricityUsage(electricityUsageData.get(CURRENT_ELECTRICITY_USAGE),electricityUsageData.get(ECONOMY_7METER), runTimeExpectedData);

    }

    public void setMainSourceOfHeating(String mainSourceOfHeating) throws InterruptedException {
        scrollIntoElementUsingJs(findElementByCss(electricityMainSourceOfHeatingNo));
        waitForAjax(1000);
        if(mainSourceOfHeating.equalsIgnoreCase("No"))
            clickElementUsingJs(findElementByCss(electricityMainSourceOfHeatingNo));
        else
            clickElementUsingJs(findElementByCss(electricityMainSourceOfHeatingYes));
    }

    public void setPaymentMode(String paymentMode) {
        requiredWait(1000);

        scrollIntoElementUsingJs(findElementByCss("#electricity-payment-method-question > div > div"));

        Select tariffValue = new Select(findElementByCss("#electricity-payment-method-dropdown-link"));
        requiredWait(1000);
        tariffValue.selectByVisibleText(paymentMode);

    }

    public void setElectricityUsage(String electricitySpend, String economy7MeterPresent, RunTimeExpectedData runTimeExpectedData){

        scrollIntoElementUsingJs(findElement(By.id("electricity-usage-question")));

        ElectricityUsage electricityUsage = new ElectricityUsage();

        if(electricitySpend.equalsIgnoreCase("kWH")){
            waitForElementToBeClickable(By.cssSelector("#electricity-usage"));
            electricityUsage.setElectricityUsagekWH(runTimeExpectedData);
            if(economy7MeterPresent.equalsIgnoreCase("Yes")){
                electricityUsage.setDayTimeElectricityUsed();
                electricityUsage.setNightTimeElectricityUsed();}
        }
        else{
            electricityUsage.setPoundSpend();
            electricityUsage.setDateOnBill();
        }

    }





}
