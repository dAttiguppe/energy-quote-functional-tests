package com.test.elements.energyDetails;

import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

import static com.test.constants.TestDataConstants.*;
import static com.test.testutils.ElementHelper.generateRandomCollectionId;
import static com.test.testutils.SeleniumDriverHelper.*;

public class YourElectricity {

    public String economy7MeterLocatorYes = "#economy-7-yes";
    public String economy7MeterLocatorNo = "#economy-7-no";
    public String electricityMainSourceOfHeatingYes = "#electricity-main-heating-yes";
    public String electricityMainSourceOfHeatingNo = "#electricity-main-heating-no";

    public static WebDriver webDriver;
    public static String gasOrElecLocator;


    public YourElectricity(){
        this.webDriver = DriverManager.getDriver();
    }

    public void setElectricityTariffDropdownValue(String electricityTariff, RunTimeExpectedData runTimeExpectedData) {
        if(runTimeExpectedData.isOnlyGasSupplier)
            gasOrElecLocator = "gas";
        else
            gasOrElecLocator = "electricity";
        waitUntilPresenceOfElementLocatedByCss("#"+ gasOrElecLocator +"-tariff-additional-info");
        waitForAjax(1000);
        Select tariffValue = new Select(findElement(By.cssSelector("#"+ gasOrElecLocator +"-tariff-additional-info")));

            List<WebElement> tariffDropdownValues =
                    findElementsByCss("#"+ gasOrElecLocator +"-tariff-additional-info > option");
            int randomDropdownVal = generateRandomCollectionId(tariffDropdownValues.size());
            Select gasTariffValue = new Select(findElement(By.cssSelector("#"+ gasOrElecLocator +"-tariff-additional-info")));
            gasTariffValue.selectByIndex(randomDropdownVal);

            String selectedOption = findElementByCss("#"+ gasOrElecLocator +"-tariff-additional-info > option[selected*=selected]")
                                        .getText();
            runTimeExpectedData.setTariff(selectedOption);

            clickAndHoldSendKeysEnter();

    }

    public void setEconomy7MeterValue(String economyMeter) {
        if(economyMeter.equalsIgnoreCase("No"))
            clickElementUsingJs(findElementByCss(economy7MeterLocatorNo));
        else
            clickElementUsingJs(findElementByCss(economy7MeterLocatorYes));

    }

    public void setenergyUsageData(HashMap<String, String> energyUsageData,
                    RunTimeExpectedData runTimeExpectedData) throws Exception {
        setElectricityTariffDropdownValue(energyUsageData.get(ENERGY_TARIFF), runTimeExpectedData);
        if(!runTimeExpectedData.isOnlyGasSupplier())
            setEconomy7MeterValue(energyUsageData.get(ECONOMY_7METER));

        setPaymentMode(energyUsageData.get(PAYMENT_MODE), runTimeExpectedData);
        setMainSourceOfHeating(energyUsageData.get(MAIN_SOURCE_OF_HEATING));

        setElectricityUsage(energyUsageData.get(CURRENT_ELECTRICITY_USAGE),energyUsageData.get(ECONOMY_7METER),
                                runTimeExpectedData);

    }

    public void setMainSourceOfHeating(String mainSourceOfHeating) throws InterruptedException {
        scrollIntoElementUsingJs(findElementByCss(electricityMainSourceOfHeatingNo));
        waitForAjax(1000);
        if(mainSourceOfHeating.equalsIgnoreCase("No"))
            clickElementUsingJs(findElementByCss(electricityMainSourceOfHeatingNo));
        else
            clickElementUsingJs(findElementByCss(electricityMainSourceOfHeatingYes));
    }

    public void setPaymentMode(String paymentMode, RunTimeExpectedData runTimeExpectedData) {
        requiredWait(1000);
        if(runTimeExpectedData.isOnlyElectricitySupplier())
            gasOrElecLocator= "electricity";
        else
            gasOrElecLocator ="gas";

        scrollIntoElementUsingJs(findElementByCss("#"+gasOrElecLocator+"-payment-method-question > div > div"));

        Select tariffValue = new Select(findElementByCss("#"+gasOrElecLocator+"-payment-method-dropdown-link"));
        requiredWait(1000);
        String dropdownFirstOption = findElementByCss("#"+gasOrElecLocator+"-payment-method-dropdown-link >option:nth-child(1)")
                                                .getText();
        tariffValue.selectByVisibleText(dropdownFirstOption);

    }

    public void setElectricityUsage(String electricitySpend, String economy7MeterPresent, RunTimeExpectedData runTimeExpectedData)
                    throws Exception{

        if(runTimeExpectedData.isOnlyElectricitySupplier() || runTimeExpectedData
                .getSameEnergySupplier().equalsIgnoreCase("Y"))
            gasOrElecLocator= "electricity";
        else
            gasOrElecLocator ="gas";

        waitForAjax(1000);
        scrollIntoElementUsingJs(findElement(By.cssSelector("#"+gasOrElecLocator+"-usage-question")));
        waitForAjax(1000);
        ElectricityUsage electricityUsage = new ElectricityUsage();
        if(electricitySpend.equalsIgnoreCase("kWH")){
            waitForElementToBeClickable(By.cssSelector("#"+gasOrElecLocator+"-usage"));
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

    public void clickkWh(RunTimeExpectedData runTimeExpectedData){
        if(runTimeExpectedData.isOnlyElectricitySupplier() || runTimeExpectedData.getSameEnergySupplier()
                        .equalsIgnoreCase("Y"))
            gasOrElecLocator= "electricity";
        else
            gasOrElecLocator ="gas";

        webDriver.findElement(By.id("electricity-usage")).click();
        webDriver.findElement(By.id("electricity-usage")).sendKeys(electricityUsage);
        for(int i=0; i<=2;i++){
        clickUsageDropDown();
        }
        webDriver.findElement(By.id("usage-dropdown")).click();
        webDriver.findElement(By.cssSelector("#electricity-usage-question #usage-dropdown > option:nth-child(4)")).click();
        webDriver.findElement(By.id("goto-your-energy")).click();
        waitForPageLoad();

        if(runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y")){
            webDriver.findElement(By.id("gas-usage")).click();
            webDriver.findElement(By.id("gas-usage")).sendKeys(gasUsage);
            webDriver.findElement(By.id("goto-your-energy")).click();}
        waitForAjax(2000);
    }

    private void clickUsageDropDown() {
        WebElement element = webDriver.findElement(By.id("usage-dropdown"));
        Actions builder = new Actions(webDriver);
        builder.moveToElement(element).clickAndHold().perform();
    }

}
