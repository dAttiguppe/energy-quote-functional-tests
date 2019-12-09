package com.test.elements.common;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

//TODO Implement Header section code
public class Header {
    public String headerLocator = "#page-header";

    public String getPageHeader(){
        return findElementByCss(headerLocator).getText();
    }

}
