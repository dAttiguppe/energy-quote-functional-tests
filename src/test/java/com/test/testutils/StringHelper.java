package com.test.testutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private final static double NO_PRICE_IN_TEXT = -1;

    public static int findIntegerInString(String text) {
        Pattern p = Pattern.compile("\\d+\\.?\\d*");
        Matcher m = p.matcher(text);
        if (m.find()) {
            return Integer.parseInt(m.group());
        } else return (int) NO_PRICE_IN_TEXT;
    }
}
