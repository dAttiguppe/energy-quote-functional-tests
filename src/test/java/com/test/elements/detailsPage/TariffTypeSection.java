package com.test.elements.detailsPage;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static com.test.testutils.SeleniumDriverHelper.clickElementUsingJs;
import static com.test.testutils.SeleniumDriverHelper.findElementByCss;

public class TariffTypeSection {

    public String fixedTariffLocator = "#tariff-selection-question > div > label.fixed-rate > span.radio-button-text >span";
    public String variableTariffLocator ="#tariff-selection-question > div > label.variable-rate.checked > span.radio-button-text > span";
    public String allTariffLocator = "#tariff-selection-question > div > label.both-rate > span.radio-button-text > span";

    private static final Logger logger = LoggerFactory.getLogger(TariffTypeSection.class);

    public TariffTypeSection(){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(com.test.testutils.DriverManager.getDriver())),
                this);
    }

    public void chooseTariff(String tariff){
        switch(tariff){
            case "FixedTariff":{
                 clickElementUsingJs(findElementByCss(fixedTariffLocator));
                break;
            }
            case "VariableTariff":{
                 clickElementUsingJs(findElementByCss(variableTariffLocator));
                break;
            }
            case "AllTarriff":{
                 clickElementUsingJs(findElementByCss(allTariffLocator));
                break;
            }
            default:
                logger.info("Not a vaild tariff selection");
        }
    }

}
