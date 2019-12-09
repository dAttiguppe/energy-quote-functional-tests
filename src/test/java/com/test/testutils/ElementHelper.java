package com.test.testutils;

import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;

public class ElementHelper {

    public static String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public static int generateRandomNumInSpecifiedRange(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end);
    }

    public static int generateRandomCollectionId(int arraySize) {
        return generateRandomNumInSpecifiedRange(0, arraySize);
    }

}
