package com.test.elements.summaryResults;

import com.test.pojo.RunTimeExpectedData;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;
import static com.test.testutils.SeleniumDriverHelper.findElementByXpath;
import static com.test.testutils.StringHelper.findIntegerInString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Summary {

    public String projection;
    private final String electricityUsageLocator = "//*[@id=\"personal-usage-amount-electricity\"]";
    private final String gasUsageLocator = "//*[@id=\"personal-usage-amount-gas\"]";

    private final String summaryPostCodeLocator = "div[ng-controller*=Controller] >p >b";
    private final String electricityProviderLocator = "p[ng-if*=ElectricityInfo] >b";
    private final String gasProviderLocator = "p[ng-if*=Gas] > b";

    public String editElectricitySupplier = "#your-details-electricity-edit-button";
    public String pageHeading = "Been here before?";

    public HtmlElement getElectricityUsageLoc() {
        return electricityUsageLoc;
    }

    public void setElectricityUsageLoc(HtmlElement electricityUsageLoc) {
        this.electricityUsageLoc = electricityUsageLoc;
    }

    public String getElectricityUsageText(){
        return electricityUsageLoc.getText().replace("edit","");
    }

    @FindBy(xpath = "//*[@id=\"personal-usage-amount-electricity\"]")
    public HtmlElement electricityUsageLoc;


    private static final Logger logger = LoggerFactory.getLogger(Summary.class);

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

    public Summary(){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(com.test.testutils.DriverManager.getDriver())),
                this);
    }

    public void compareSummaryDetails(RunTimeExpectedData runTimeExpectedData){
        //assertEquals(true, findElementByCss(summaryPostCodeLocator).getText().toLowerCase().equalsIgnoreCase(runTimeExpectedData.getPostCode()));
        assertEquals(true, getGasSupplierSummarySection().equalsIgnoreCase(runTimeExpectedData.getGasSupplierName().replaceAll("-","")));
        //TODO assertEquals(true, getElectricityUsageText().replaceAll("kWh","").equalsIgnoreCase(runTimeExpectedData.getElectricityUsage()));
        assertTrue(getElectricalSupplierSummarySection().toLowerCase().
                equalsIgnoreCase(runTimeExpectedData.getElectricalSupplierName().replaceAll("-","")));

        assertEquals(true, String.valueOf(
                findIntegerInString(getSummarySectionGasUsage().toLowerCase())).
                equalsIgnoreCase(runTimeExpectedData.getGasUsage().replaceAll("-", "")));
    }


    public void editElectricitySupplier() {
        findElementByCss(editElectricitySupplier).click();
    }
}
