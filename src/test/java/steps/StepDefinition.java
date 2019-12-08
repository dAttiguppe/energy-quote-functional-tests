package steps;

import com.test.elements.detailsPage.AvailableTariffs;
import com.test.elements.detailsPage.UserPreferences;
import com.test.elements.energyDetails.YourElectricity;
import com.test.elements.filtersSection.Filters;
import com.test.elements.summaryResults.Summary;
import com.test.elements.summaryResults.SummaryResults;
import com.test.elements.supplierDetails.SupplierDetails;
import com.test.pojo.RunTimeExpectedData;
import com.test.testutils.SeleniumDriverHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.test.constants.TestDataConstants.*;
import static com.test.testutils.DriverManager.embedScreenshot;
import static com.test.testutils.DriverManager.embedScreenshotIfFailed;
import static com.test.testutils.SeleniumDriverHelper.*;
import static org.junit.Assert.assertTrue;

public class StepDefinition {

    private static WebDriver driver;
    SupplierDetails supplierDetails = new SupplierDetails();
    UserPreferences userPreferences = new UserPreferences();
    RunTimeExpectedData runTimeExpectedData = new RunTimeExpectedData();
    AvailableTariffs availableTariffs = new AvailableTariffs();
    Summary summary = new Summary();
    SummaryResults summaryResults = new SummaryResults();

    private static final Logger logger = LoggerFactory.getLogger(StepDefinition.class);

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void initTest() throws Exception {
        initialize();
        com.test.testutils.DriverManager.getDriver().get(WEBSITE_URL);
    }

    @After
    public void afterStep(Scenario scenario) {
        embedScreenshot(scenario);
    }

    @After
    public void tearDownTest(Scenario scenario) {
        embedScreenshotIfFailed(scenario);
        tearDown();
    }

    @After
    public void clearCookies() {
        deleteAllCookies();
    }

    @Given("the user fills the supplier details")
    public void theUserFillsTheSupplierDetails(DataTable supplierDetailsDT) throws Exception{
        initTest();
        List<Map<String, String>> list = supplierDetailsDT.asMaps(String.class, String.class);
        String billPresent = list.get(0).get("billPresent");
        String energyToBeCompared = list.get(0).get(ENERGY_TO_BE_COMPARED);
        String energyFromSameSupplier = list.get(0).get(ENERGY_FROM_SAME_SUPPLIER);
        String currentSupplier = list.get(0).get(CURRENT_SUPPLIER);

        supplierDetails.user_Enters_PostCode(runTimeExpectedData);
        supplierDetails.user_Has_Bill(billPresent,runTimeExpectedData);
        supplierDetails.user_Selects_Energy_ToBe_Compared(energyToBeCompared,runTimeExpectedData);
        supplierDetails.user_Selects_Gas_And_Electricity_From_Same_Supplier(energyFromSameSupplier,runTimeExpectedData);
        supplierDetails.user_Chooses_Current_Supplier(currentSupplier,runTimeExpectedData);
    }


    @When("the user proceeds to the Your energy page")
    public void user_Proceeds_To_Next_Page(){
        supplierDetails.clickNextButton1();
        waitForAjax(500);
        assertTrue(supplierDetails.getCurrentPageClass().contains("current"));
    }

    @And("the user selects the your electricity fields")
    public void user_Selects_Electricity_Usage_Values(DataTable yourElectricity) throws Exception {
        List<Map<String, String>> list = yourElectricity.asMaps(String.class, String.class);
        YourElectricity userElectricityUsage = new YourElectricity();
        HashMap<String, String> electricityUsageData = new HashMap<String, String>();

        electricityUsageData.put(ELECTRICITY_TARIFF,list.get(0).get(ELECTRICITY_TARIFF));
        electricityUsageData.put(ECONOMY_7METER,list.get(0).get(ECONOMY_7METER));
        electricityUsageData.put(PAYMENT_MODE,list.get(0).get(PAYMENT_MODE));
        electricityUsageData.put(MAIN_SOURCE_OF_HEATING,list.get(0).get(MAIN_SOURCE_OF_HEATING));
        electricityUsageData.put(CURRENT_ELECTRICITY_USAGE,list.get(0).get(CURRENT_ELECTRICITY_USAGE));
        userElectricityUsage.setElectricityUsageData(electricityUsageData,runTimeExpectedData);
    }

    @And("the user clicks on Next button")
    public void clickNextButton(){
        scrollIntoElementUsingJs(findElementByCss("#goto-your-energy"));
        waitForAjax(1000);
        findElementByCss("#goto-your-energy").click();
    }

    @When("the user proceeds to the your preferences page")
    public void user_Proceeds_To_Next_Page1(){
        waitForAjax(1000);
        assertTrue(findElementByXpath("//*[@id=\"nav-your-supplier\"][contains(@ng-switch,'Enabled')]").getAttribute("ng-switch").contains("Enabled"));
        assertTrue(findElementByXpath("//*[@id=\"nav-your-energy\"][contains(@ng-switch,'Enabled')]").getAttribute("ng-switch").contains("Enabled"));
        assertTrue(findElementByCss("body > div > div > main > section:nth-child(1) > div.section-header > h2").
                getText().equalsIgnoreCase("Your preferences"));

    }

    @Then("the user proceeds to the summary page")
    public void user_Proceeds_To_Summary_Page(){
        scrollIntoElementUsingJs(findElementByCss("#email-submit"));
        waitForAjax(500);
        clickElementUsingJs(findElementByCss("#email-submit"));
        assertTrue(SeleniumDriverHelper.getTextByXpath("/html/body/div/div/main/div[2]/section[1]/div/section[1]/div[1]/h3")
                .equalsIgnoreCase("Summary"));

    }

    @And("the user selects the preferences")
    public void user_Selects_Preferences(DataTable dtPreferences){
        List<Map<String, String>> list = dtPreferences.asMaps(String.class, String.class);
        HashMap<String, String> preferredData = new HashMap<String, String>();

        preferredData.put(TARIFF_TYPE,list.get(0).get(TARIFF_TYPE));
        preferredData.put(EMAILD_ADDRESS,list.get(0).get(EMAILD_ADDRESS));
        preferredData.put(PAYMENT_MODE,list.get(0).get(PAYMENT_MODE));

        userPreferences.selectTariffType(preferredData);
        userPreferences.selectPaymentType(preferredData.get(PAYMENT_MODE));
        userPreferences.setEmailID(preferredData.get(EMAILD_ADDRESS));

    }

    @And("the user confirms to the TermsAndConditions")
    public void the_user_confirms_to_the_TermsAndConditions() {
        userPreferences.acceptTermsAndConditions();
    }

    @Given("the user checks the summary section on the Summary page")
    public void user_Checks_SummarySection_On_SummaryPage() {
        summary.compareSummaryDetails(runTimeExpectedData);
    }

    @When("the user chooses filters")
    public void the_User_Chooses_Filters(DataTable filtersTable) {
        List<Map<String, String>> list = filtersTable.asMaps(String.class, String.class);
        HashMap<String, String> filterData = new HashMap<String, String>();

        filterData.put(FILTER_TYPE,list.get(0).get(FILTER_TYPE));
        filterData.put(FILTER_VALUE,list.get(0).get(FILTER_VALUE));
        Filters filters = new Filters();
        filters.testTariffTypeFilter(filterData);
    }

    @When("the user clicks on the More Tariffs button")
    public void user_Clicks_More_Tariffs(){
        availableTariffs.clickMoreTariffsSection();
        assertTrue(availableTariffs.isAllOurTariffsPresent());
    }

    @And("the user clicks on the Include the providers we can’t switch you to button")
    public void theUserClicksOnTheIncludeTheProvidersWeCanTSwitchYouToButton() {
        availableTariffs.clickIncludeProvidersCantSwitchTo();
    }

    @Then("All the available tariffs should be displayed")
    public void all_The_Available_Tariffs_Should_Be_Displayed(){
        availableTariffs.verifyTariffsIncludeProvidersCantSwitchTo();
    }

    @Then("all the available tariffs should be displayed")
    public void allTheAvailableTariffsShouldBeDisplayed() {
    }

    @When("the user filters by (.*) supplier rating filter")
    public void user_Filters_By_Supplier_Rating(String supplierRatingFilter) {
        Filters filters = new Filters();
        assertTrue(filters.selectSupplierRatingFilter(supplierRatingFilter));
    }

    @And("the user clicks on the checks more details about the quote")
    public void user_Clicks_More_Details_Quote() {
        summaryResults.checkQuoteMoreDetails();
    }

    @Then("all the more details section should be displayed")
    public void allTheMoreDetailsSectionShouldBeDisplayed() {
        Assert.assertTrue(findElementByCss(summaryResults.headerTextLocator).getText().contains(summaryResults.headerText));
    }

    @When("the user edits the electricity supplier")
    public void user_Edits_The_Electricity_Supplier() {
        summary.editElectricitySupplier();
        Assert.assertFalse(findElementByCss("div > h3").isDisplayed());
    }

    @Then("the user lands on the Your Supplier page")
    public void theUserLandsOnTheYourSupplierPage() {
        Assert.assertTrue(findElementByCss("div.section-header > h2").getText().equalsIgnoreCase(summary.pageHeading));
    }

}
