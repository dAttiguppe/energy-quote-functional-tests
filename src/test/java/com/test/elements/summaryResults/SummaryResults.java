package com.test.elements.summaryResults;

import static com.test.testutils.SeleniumDriverHelper.clickElementUsingJs;
import static com.test.testutils.SeleniumDriverHelper.findElementByXpath;

public class SummaryResults {

    public String moreDetailsButton = "//td[contains(@class,'more')]/div/button[1]";
    public String headerTextLocator = "tr.bridging-page-row.ng-isolate-scope > td > section > header";
    public final String headerText = "You won’t get this tariff cheaper going direct";

    public void checkQuoteMoreDetails() {
        clickElementUsingJs(findElementByXpath(moreDetailsButton));
    }

}
