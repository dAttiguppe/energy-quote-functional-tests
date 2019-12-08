package com.test.testutils;

import org.openqa.selenium.WebElement;

public class ElementHelper {

    public static String getTextFromElement(WebElement element) {
        return element.getText();
    }

}
