package com.test.elements.energyDetails;

import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.DriverManager;
import org.openqa.selenium.WebDriver;

import static com.test.constants.TestDataConstants.electricityUsage;
import static com.test.constants.TestDataConstants.gasUsage;
import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

public class ElectricityUsage {
    YourElectricity yourElectricity = new YourElectricity();
    private static WebDriver webDriver;

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

    public ElectricityUsage() throws Exception {
        this.webDriver = DriverManager.getDriver();
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

    public void setElectricityUsagekWH(RunTimeExpectedData runTimeExpectedData) throws Exception {

        if (runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y")) {
            runTimeExpectedData.setElectricityUsage(electricityUsage);
            runTimeExpectedData.setGasUsage(gasUsage);
        }
        if(runTimeExpectedData.isOnlyElectricitySupplier){
            runTimeExpectedData.setElectricityUsage(electricityUsage);
        }
        if(runTimeExpectedData.isOnlyGasSupplier()){
            runTimeExpectedData.setGasUsage(gasUsage);
        }

        webDriver.switchTo().defaultContent();
//        if(findElementByCss("#your-energy > section:nth-child(2) > div.section-header > h1").getText()
//                                    .equalsIgnoreCase("Your gas")){
//            findElementByCss("#gas-usage").sendKeys("100");
//            waitForAjax(1000);
//        }
//        else
//            setValueUsingJs(findElementByCss(electricityUsagekWHLocator),"100");

        yourElectricity.clickkWh(runTimeExpectedData);
    }

}
