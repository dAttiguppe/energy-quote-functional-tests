package com.test.elements.supplierDetails;

import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.SeleniumDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.test.constants.TestDataConstants.*;
import static com.test.testutils.SeleniumDriverHelper.*;
import static org.junit.Assert.assertTrue;

public class SupplierDetails {

    public String billPresentLocator = "//label[contains(@id,'have')]/input";
    public String billNotPresentLocator = "//label[contains(@id,'no')]/input";

    public String noBillCSSLoc = "#no-bill";
    public void getNoBillCSSLoc(){
        findElementByCss(noBillCSSLoc).click();
    }

    public String compareGasAndElectricityLocator = "#compare-what-both";
    public String compareGasLocator = "#compare-what-gas";
    public String compareElectricityLocator = "#compare-what-electricity";

    public String energyFromSameSupplierYesLocator = "#same-supplier-yes";
    public String energyFromSameSupplierNoLocator = "#same-supplier-no";

    private static final Logger logger = LoggerFactory.getLogger(SupplierDetails.class);

    public void user_Enters_PostCode(RunTimeExpectedData runTimeExpectedData) throws Exception {
        System.out.println("User enters post code");

        findElementByCss("#your-postcode").
                sendKeys(POST_CODE);
        clickElementUsingJs(findElementByXpath("//*[@id=\"find-postcode\"]"));

        requiredWait(1000);
        String postCode = findElement(By.xpath("//*[@id=\"postcode-question\"]/div/div[1]/div[2]/div/span")).getText();
        assertTrue(POST_CODE.equalsIgnoreCase(postCode));
        runTimeExpectedData.setPostCode(POST_CODE);
    }

    public void user_Has_Bill(String billPresent, RunTimeExpectedData runTimeExpectedData) {
        scrollIntoElementUsingJs(getBillNotPresent());
        if (billPresent.equalsIgnoreCase(ENERGY_BILL_NOT_PRESENT)) {
            waitUntilPresenceOfElementLocatedByCss(noBillCSSLoc, 500);
            clickElementUsingJs(getBillNotPresent());
            runTimeExpectedData.setUserBillPresent(ENERGY_BILL_NOT_PRESENT);
        } else {
            clickBillPresent();
            runTimeExpectedData.setUserBillPresent(ENERGY_BILL_PRESENT);
        }
    }

    public void clickCompareGasAndElectricity(){
        clickElementUsingJs(findElementByCss(compareGasAndElectricityLocator));
    }

    public void clickCompareGas(){
        clickElementUsingJs(findElementByCss(compareGasLocator));
    }

    public void clickCompareElectricity(){
        clickElementUsingJs(findElementByCss(compareElectricityLocator));
    }

    public void clickEnergyFromSameSupplierYes(){
        clickElementUsingJs(findElementByCss(energyFromSameSupplierYesLocator));
    }

    public void clickEnergyFromSameSupplierNo(){
        clickElementUsingJs(findElementByCss(energyFromSameSupplierNoLocator));
    }


    public void clickEnergyProvider(String energyProvider) {
        waitForAjax(1000);
        scrollIntoElementUsingJs(findElementByCss("#dual-energy-suppliers-question"));
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss("#dual-top-six-"+energyProvider+""));
        waitForAjax(1000);
        assertTrue(findElementByCss("#dual-energy-suppliers-question > div > div > div.radio-buttons.flex-column > label[class*=checked]")
                .getAttribute("class").contains("checked"));
    }

    public void user_Selects_Energy_ToBe_Compared(String energyToBeCompared, String currentSupplier,
                                                  RunTimeExpectedData runTimeExpectedData) {

        switch(energyToBeCompared){
            case GAS: {
                scrollIntoElementUsingJs( getBillNotPresent());
                clickCompareGas();
                runTimeExpectedData.setOnlyGasSupplier(true);
                clickGasSupplier(currentSupplier);
                runTimeExpectedData.setGasSupplierName(currentSupplier);
                break;
            }
            case ELECTRICITY: {
                clickCompareElectricity();
                runTimeExpectedData.setOnlyElectricitySupplier(true);
                clickElectricitySupplier(currentSupplier);
                runTimeExpectedData.setElectricitySupplier(currentSupplier);
                break;
            }
            case GAS_AND_ELECTRICITY: {
                clickCompareGasAndElectricity();
                runTimeExpectedData.setSameEnergySupplier("Y");
                clickEnergyProvider(currentSupplier);
                runTimeExpectedData.setElectricalSupplierName(currentSupplier);
                runTimeExpectedData.setGasSupplierName(currentSupplier);
                break;
            }
            default:
                logger.info("Not a valid selection");
        }
    }


    public void user_Selects_Gas_And_Electricity_From_Same_Supplier(String energyFromSameSupplier, RunTimeExpectedData runTimeExpectedData){

        switch(energyFromSameSupplier){
            case ENERGY_FROM_SAME_SUPPLIER_Y:{
                 clickEnergyFromSameSupplierYes();
                runTimeExpectedData.setSameEnergySupplier(ENERGY_FROM_SAME_SUPPLIER_Y);
                break;}
            case ENERGY_FROM_SAME_SUPPLIER_N:{
                 clickEnergyFromSameSupplierNo();
                 runTimeExpectedData.setSameEnergySupplier(ENERGY_FROM_SAME_SUPPLIER_N);
                break;}
            default:
                logger.info("Not a valid selection");
        }
    }


    public void user_Chooses_Current_Supplier(String currentSupplier, RunTimeExpectedData runTimeExpectedData){

        if(runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y")) {
            clickEnergyProvider(currentSupplier);
            runTimeExpectedData.setElectricalSupplierName(currentSupplier);
            runTimeExpectedData.setGasSupplierName(currentSupplier);
        }
        if(runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("N")){
            if(runTimeExpectedData.isOnlyGasSupplier()){
                clickGasSupplier(currentSupplier);
                runTimeExpectedData.setGasSupplierName(currentSupplier);
            }
        }
        if(runTimeExpectedData.isOnlyElectricitySupplier()){
            clickElectricitySupplier(currentSupplier);
            runTimeExpectedData.setElectricitySupplier(currentSupplier);
        }
    }

    private void clickGasSupplier(String currentSupplier) {

        waitForAjax(1000);
        scrollIntoElementUsingJs(findElementByCss("#gas-energy-suppliers-question"));
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss("#dual-top-six-"+currentSupplier+""));
        waitForAjax(1000);
        assertTrue(findElementByCss("#gas-energy-suppliers-question > div > div > div.radio-buttons.flex-column > label[class*=checked]")
                .getAttribute("class").contains("checked"));

    }

    private void clickElectricitySupplier(String currentSupplier) {

        waitForAjax(1000);
        scrollIntoElementUsingJs(findElementByCss("#elec-energy-suppliers-question"));
        waitForAjax(1000);
        clickElementUsingJs(findElementByCss("#dual-top-six-"+currentSupplier+""));
        waitForAjax(1000);
        assertTrue(findElementByCss("#elec-energy-suppliers-question > div > div > div.radio-buttons.flex-column > label[class*=checked]")
                .getAttribute("class").contains("checked"));

    }



    public WebElement getBillPresent(){
        return SeleniumDriverHelper.findElementByXpath(billPresentLocator);
    }

    public WebElement getBillNotPresent(){
        return SeleniumDriverHelper.findElementByXpath(billNotPresentLocator);
    }


    public void clickBillPresent(){
        clickElementUsingJs(getBillPresent());
    }

    public void clickNextButton1() {
        List<WebElement> navigationTabs = SeleniumDriverHelper.findElements(By.cssSelector("#header-container > section > ul > li"));


        clickElementUsingJs(findElementByCss("#goto-your-supplier-details"));
        SeleniumDriverHelper.requiredWait(100);
    }

    public void clickNextButton() {
        List<WebElement> navigationTabs = SeleniumDriverHelper.findElements(By.cssSelector("#header-container > section > ul > li"));


        clickElementUsingJs(findElementByCss("#goto-your-energy"));
        SeleniumDriverHelper.requiredWait(100);
    }

    public String getCurrentPageClass() {
        waitForAjax(1000);
        return findElementByCss("#nav-your-energy > span[class*=current]").getAttribute("class");
    }

    public void clickHeader(){
        waitForElementToBeClickable(findElementByCss("#header-container > section > button"),100);
        clickElementUsingJs(findElementByCss("#header-container > section > button"));
    }
}
