package com.test.elements.common;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

//TODO Implement Footer section code
public class Footer {
    public String footerLocator = "body > footer";

    public String getFooterLocator(){
        return findElementByCss(footerLocator).getText();
    }
}
