package com.test.elements.detailsPage;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static com.test.testutils.SeleniumDriverHelper.findElementByCss;
import static com.test.testutils.SeleniumDriverHelper.scrollIntoElementUsingJs;

public class PaymentTypes {

    private static final Logger logger = LoggerFactory.getLogger(PaymentTypes.class);

    public PaymentTypes(){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(com.test.testutils.DriverManager.getDriver())),
                this);
    }

    public void choosePaymentType(String paymentMode){
        scrollIntoElementUsingJs(findElementByCss("#payment-selection-question > div > div.radio-buttons.flex-column > label"));
        findElementByCss("#payment-selection-question > div > div.radio-buttons.flex-column > label[for*="+paymentMode+"]")
                    .click();
    }

}
