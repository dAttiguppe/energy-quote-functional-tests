package com.test.elements.summaryResults;

import com.test.elements.common.TariffEndDateModal;
import com.test.pojo.RunTimeExpectedData;

import static com.test.testutils.SeleniumDriverHelper.*;
import static com.test.testutils.StringHelper.findIntegerInString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Summary {
    TariffEndDateModal tariffEndDateModal = new TariffEndDateModal();

    public String projection;
    private final String electricityUsageLocator = "//*[@id=\"personal-usage-amount-electricity\"]";
    private final String gasUsageLocator = "//*[@id=\"personal-usage-amount-gas\"]";

    private final String summaryPostCodeLocator = "div[ng-controller*=Controller] >p >b";
    private final String electricityProviderLocator = "p[ng-if*=ElectricityInfo] >b";
    private final String gasProviderLocator = "p[ng-if*=Gas] > b";

    public String editElectricitySupplier = "#your-details-electricity-edit-button";
    public String pageHeading = "Been here before?";

    public String getElectricalSupplierSummarySection() {
        return findElementByCss(electricityProviderLocator).getText().replace("edit","");
    }

    public String getGasSupplierSummarySection() {
        return findElementByCss(gasProviderLocator).getText().replaceAll("edit","");
    }

    public String getProjection() {
        return projection;
    }

    public String getElectricityUsage() {
        return findElementByXpath(electricityUsageLocator).getText().replace("edit","");
    }

    public String getSummarySectionGasUsage() {
        return findElementByXpath(gasUsageLocator).getText().replace("edit","");
    }

    public void compareSummaryDetails(RunTimeExpectedData runTimeExpectedData){

        requiredWait(1000);
        waitForPageLoad();
        if(tariffEndDateModal.isTariffModalPresent()) {
            updateTariffModal(runTimeExpectedData);
        }

        else {

            assertEquals(true, findElementByCss(summaryPostCodeLocator).getText().
                    toLowerCase().equalsIgnoreCase(runTimeExpectedData.getPostCode()));
            assertEquals(true, getGasSupplierSummarySection().
                    equalsIgnoreCase(runTimeExpectedData.getGasSupplierName().replaceAll("-", "")));
            //TODO assertEquals(true, getElectricityUsageText().replaceAll("kWh","").equalsIgnoreCase(runTimeExpectedData.getElectricityUsage()));
            assertTrue(getElectricalSupplierSummarySection().toLowerCase().
                    equalsIgnoreCase(runTimeExpectedData.getElectricalSupplierName().replaceAll("-", "")));
            //if(runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y"))
            requiredWait(1000);
            assertEquals(true, String.valueOf(
                    findIntegerInString(getSummarySectionGasUsage().toLowerCase())).
                    equalsIgnoreCase(runTimeExpectedData.getGasUsage().replaceAll("-", "")));
        }
    }

    private void updateTariffModal(RunTimeExpectedData runTimeExpectedData) {
        if(runTimeExpectedData.isOnlyGasSupplier()){
            tariffEndDateModal.clickGasTariffExpiryDateLocator();
            tariffEndDateModal.clickUpdateResultsButton();
            waitForPageLoad();
        }
        if(runTimeExpectedData.isOnlyElectricitySupplier()) {
            tariffEndDateModal.clickElecTariffExpiryDateLocator();
            tariffEndDateModal.clickUpdateResultsButton();
            waitForPageLoad();
        }
        if(runTimeExpectedData.getSameEnergySupplier().equalsIgnoreCase("Y"))
        {
            tariffEndDateModal.clickGasTariffExpiryDateLocator();
            tariffEndDateModal.clickElecTariffExpiryDateLocator();
            tariffEndDateModal.clickUpdateResultsButton();
            waitForPageLoad();
        }
    }

    public void editElectricitySupplier() {
        findElementByCss(editElectricitySupplier).click();
    }
}
