package com.test.elements.detailsPage;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;
import static com.test.testutils.SeleniumDriverHelper.scrollIntoElementUsingJs;

public class PaymentTypes {

    public void choosePaymentType(String paymentMode){
        scrollIntoElementUsingJs(findElementByCss("#payment-selection-question > div > div.radio-buttons.flex-column > label"));
        findElementByCss("#payment-selection-question > div > div.radio-buttons.flex-column > label[for*="+paymentMode+"]")
                    .click();
    }

}
