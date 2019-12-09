package com.test.elements.common;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

public class BeenHereBefore {

    public String previousEnergyQuoteLink = "#sign-in-prompt-link";

    public void clickPreviousEnergyQuoteLink() {
        findElementByCss(previousEnergyQuoteLink).click();
    }

}
