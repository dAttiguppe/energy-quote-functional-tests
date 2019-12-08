package com.test.elements.filtersSection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.HashMap;
import java.util.List;

import static com.test.constants.TestDataConstants.FILTER_TYPE;
import static com.test.constants.TestDataConstants.TARIFF_TYPE;
import static com.test.testutils.SeleniumDriverHelper.*;
import static org.junit.Assert.assertTrue;

public class Filters {

    public String tariffTypeFilterLocator = "#tariffs-for-me > div > div > div.filter-results-inner.ng-scope > section:nth-child(2) > div";
    public String checkedFilterLocator = "#filters-tariff-type-fixed[class*=checked]";

    private static final Logger logger = LoggerFactory.getLogger(Filters.class);

    public void testTariffTypeFilter(HashMap<String, String> filterData){
        if(filterData.get(FILTER_TYPE).equalsIgnoreCase(TARIFF_TYPE)) {
            scrollToElementUsingJs(findElementByCss(tariffTypeFilterLocator));
            getTextByCss(checkedFilterLocator);

            List<WebElement> summaryResultRows = findElementsByCss("#tariffs-for-me > div > section > table >tbody");
            summaryResultRows.size();
            assertTrue(findElementsByCss("#tariffs-for-me > div > section > table >tbody").
                    stream().anyMatch(s->s.findElement(By.cssSelector("tr:nth-child(2)")).getText().contains("Fixed")));

            assertTrue(summaryResultRows
                    .stream()
                    .anyMatch(s->s.findElement(By.cssSelector("td.td.tariff-feature-payment-method > p"))
                    .getText()
                    .equalsIgnoreCase("Monthly")));

            //assertTrue(getTextByCss(tariffTypeFilterLocator).contains(summarySectionTariffType));
            }
        }

    public boolean selectSupplierRatingFilter(String supplierRatingFilter) {
            String filterLocator = "label[id*=filters-supplier-rating-"+supplierRatingFilter+"]";
            scrollToElementUsingJs(findElementByCss(filterLocator));
            clickElementUsingJs(findElementByCss(filterLocator));
            return findElementByCss(filterLocator).getAttribute("class").contains("checked");
    }
}
